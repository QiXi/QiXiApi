package ru.qixi.api.events.v2;

import java.util.HashMap;
import java.util.Map;


public class EventDispatcher implements IEventDispatcher {

	private Map<String, IEventListener>	mListeners;
	private IEventDispatcher			mParentDispatcher;
	private String						mClassName;


	public EventDispatcher() {
		this(null, "");
	}


	public EventDispatcher(final IEventDispatcher pDispatcher) {
		this(pDispatcher, null);
	}


	public EventDispatcher(final IEventDispatcher pDispatcher, final String pClassName) {
		mParentDispatcher = pDispatcher;
		mListeners = new HashMap<String, IEventListener>();
		mClassName = pClassName;
	}


	@Override
	public void addEventListener(IEventType pEventType, IEventListener pEventListener) {
		if (!mListeners.containsKey(pEventType.toString())) {
			mListeners.put(pEventType.toString(), pEventListener);
		}
	}


	@Override
	public boolean hasEventListener(IEventType pEventType) {
		return mListeners.containsKey(pEventType.toString());
	}


	@Override
	public void removeEventListener(IEventType pEventType) {
		mListeners.remove(pEventType.toString());
	}


	@Override
	public void dispatchEvent(IEvent pEvent) {
		if (mListeners.containsKey(pEvent.getType().toString())) {
			final IEventListener listener = mListeners.get(pEvent.getType().toString());
			listener.handleEvent(pEvent);
		}
		if (mParentDispatcher != null) {
			mParentDispatcher.dispatchEvent(pEvent);
		}
	}


	public String getClassName() {
		return mClassName;
	}
}
