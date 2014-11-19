package ru.qixi.api.events.v3;

import android.os.Handler;
import android.os.Message;


public class HandlerEventDispatcher extends EventDispatcher {

	static final int	MSG_EVENT			= 9991;
	static final int	MSG_CAPTURE_EVENT	= 9992;

	private Handler		mHandler			= new Handler(new IncomingHandlerCallback());
	private Thread		mUiThread			= Thread.currentThread();

	
	public HandlerEventDispatcher(final String pName) {
		super(pName);
	}


	public HandlerEventDispatcher(final String pName, final IEventDispatcher pDispatcher) {
		super(pName, pDispatcher);
	}


	@Override
	public void dispatchEvent(IEvent pEvent) {
		//Log.i("Event", Thread.currentThread().getName() + " " + pEvent.toString());
		if (Thread.currentThread() == mUiThread) {
			super.dispatchEvent(pEvent);
		} else {
			Message msg = mHandler.obtainMessage(MSG_EVENT, pEvent);
			mHandler.sendMessage(msg);
		}
	}


	@Override
	public void dispatchCaptureEvent(IEvent pEvent) {
		//Log.i("CaptureEvent", Thread.currentThread().getName() + " " + pEvent.toString());
		if (Thread.currentThread() == mUiThread) {
			super.dispatchCaptureEvent(pEvent);
		} else {
			Message msg = mHandler.obtainMessage(MSG_CAPTURE_EVENT, pEvent);
			mHandler.sendMessage(msg);
		}
	}


	private void dispatchEvent(Message pMessage) {
		//Log.i("dispatchEvent", Thread.currentThread().getName());
		IEvent event = (IEvent) pMessage.obj;
		super.dispatchEvent(event);
	}


	private void dispatchCaptureEvent(Message pMessage) {
		//Log.i("dispatchCaptureEvent", Thread.currentThread().getName());
		IEvent event = (IEvent) pMessage.obj;
		super.dispatchCaptureEvent(event);
	}


	private class IncomingHandlerCallback implements Handler.Callback {

		@Override
		public boolean handleMessage(Message pMessage) {
			switch (pMessage.what) {
			case MSG_EVENT:
				dispatchEvent(pMessage);
				break;
			case MSG_CAPTURE_EVENT:
				dispatchCaptureEvent(pMessage);
				break;
			default:
				break;
			}

			return true;
		}

	}
}
