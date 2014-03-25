package ru.qixi.api.events.v3;

import android.os.Handler;
import android.os.Message;

public class HandlerEventDispatcher extends EventDispatcher {

	private Handler	mHandler;


	public HandlerEventDispatcher() {
		super();
		init();
	}


	public HandlerEventDispatcher(final String pClassName) {
		super(pClassName);
		init();
	}


	public HandlerEventDispatcher(final IEventDispatcher pDispatcher) {
		super(pDispatcher);
		init();
	}


	public HandlerEventDispatcher(final String pClassName, final IEventDispatcher pDispatcher) {
		super(pClassName, pDispatcher);
		init();
	}


	private void init() {
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				dispatchEvent(msg);
			}
		};
	}


	private void dispatchEvent(Message msg) {
		IEvent event = (IEvent)msg.obj;
		super.dispatchEvent(event);
		msg.recycle();
	}


	@Override
	public void dispatchEvent(IEvent pEvent) {
		Message msg = mHandler.obtainMessage(pEvent.getType(), pEvent);
		mHandler.sendMessage(msg);
	}


	@Override
	public void dispatchCaptureEvent(IEvent pEvent) {

	}

}
