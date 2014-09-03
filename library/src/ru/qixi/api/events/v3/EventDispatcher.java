package ru.qixi.api.events.v3;

import java.util.ArrayList;

import ru.qixi.api.util.Log;

import android.util.SparseArray;


public class EventDispatcher implements IEventDispatcher {

	private static final String			DEFAULT_NAME	= EventDispatcher.class.getSimpleName();

	private String						mClassName;
	private SparseArray<IEventListener>	mBubbleListeners;
	private SparseArray<IEventListener>	mCaptureListeners;
	private ArrayList<IEventDispatcher>	mClients;
	private IEventDispatcher			mParentDispatcher;


	public EventDispatcher() {
		this(DEFAULT_NAME, null);
	}


	public EventDispatcher(String pClassName) {
		this(pClassName, null);
	}


	public EventDispatcher(IEventDispatcher pDispatcher) {
		this(DEFAULT_NAME, pDispatcher);
	}


	public EventDispatcher(String pClassName, IEventDispatcher pDispatcher) {
		//Log.d("new EventDispatcher name:[%s]", pClassName);
		mClassName = pClassName;
		if (pDispatcher != null) {
			mParentDispatcher = pDispatcher;
			mParentDispatcher.registerClient(this);
		}
		mClients = new ArrayList<IEventDispatcher>();
		mBubbleListeners = new SparseArray<IEventListener>();
		mCaptureListeners = new SparseArray<IEventListener>();
	}


	@Override
	public void addEventListener(int pEventType, IEventListener pEventListener) {
		mBubbleListeners.put(pEventType, pEventListener);
	}


	@Override
	public void addEventListener(int pEventType, IEventListener pEventListener, boolean pUseCapture) {
		if (pUseCapture) {
			mCaptureListeners.put(pEventType, pEventListener);
		} else {
			mBubbleListeners.put(pEventType, pEventListener);
		}
	}


	@Override
	public boolean hasEventListener(int pEventType) {
		return mBubbleListeners.get(pEventType) != null;
	}


	@Override
	public boolean hasEventListener(int pEventType, boolean pUseCapture) {
		if (pUseCapture) {
			return mCaptureListeners.get(pEventType) != null;
		} else {
			return mBubbleListeners.get(pEventType) != null;
		}
	}


	@Override
	public void removeEventListener(int pEventType) {
		mBubbleListeners.remove(pEventType);
	}


	@Override
	public void removeEventListener(int pEventType, boolean pUseCapture) {
		if (pUseCapture) {
			mCaptureListeners.remove(pEventType);
		} else {
			mBubbleListeners.remove(pEventType);
		}
	}


	@Override
	public void dispatchEvent(IEvent pEvent) {
		int type = pEvent.getType();
		final IEventListener listener = mBubbleListeners.get(type);
		if (listener != null) {
			listener.handleEvent(pEvent);
		}
		if (mParentDispatcher != null) {
			mParentDispatcher.dispatchEvent(pEvent);
		} else {
			pEvent.setPhase(EventPhase.CAPTURING);
			dispatchCaptureEvent(pEvent);
		}
	}


	@Override
	public void registerClient(IEventDispatcher pEventDispatcher) {
		//Log.d("registerClient pEventDispatcher:[%s]", pEventDispatcher);
		mClients.add(pEventDispatcher);
	}


	@Override
	public void unregisterClient(IEventDispatcher pEventDispatcher) {
		//Log.d("unregisterClient pEventDispatcher:[%s]", pEventDispatcher);
		mClients.remove(pEventDispatcher);
	}


	@Override
	public void dispatchCaptureEvent(IEvent pEvent) {
		int type = pEvent.getType();
		final IEventListener listener = mCaptureListeners.get(type);
		if (listener != null) {
			listener.handleEvent(pEvent);
		}
		for (int i = mClients.size() - 1; i >= 0; i--) {
			mClients.get(i).dispatchCaptureEvent(pEvent);
		}
		if (mParentDispatcher == null) {
			pEvent.onFinish();
		}
	}


	@Override
	public String toString() {
		return mClassName;
	}


	@Override
	public void setParentDispatcher(IEventDispatcher pEventDispatcher) {
		if (pEventDispatcher != null) {
			mParentDispatcher = pEventDispatcher;
			mParentDispatcher.registerClient(this);
		}
	}

}
