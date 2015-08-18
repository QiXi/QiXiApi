package ru.qixi.api.events.v3;

public class BaseEventListener implements IEventListener {

	private Object	mParentObject;


	public BaseEventListener() {
		mParentObject = null;
	}


	public BaseEventListener(Object pParentObject) {
		mParentObject = pParentObject;
	}


	public Object getParentObject() {
		return mParentObject;
	}


	@Override
	public void handleEvent(IEvent pEvent) {

	}


	public String toString() {
		return mParentObject == null ? "{ Anonymous listener }" : mParentObject.toString();
	}
}
