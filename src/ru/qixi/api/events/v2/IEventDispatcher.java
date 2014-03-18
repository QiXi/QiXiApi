package ru.qixi.api.events.v2;

public abstract interface IEventDispatcher {

	public abstract void addEventListener(IEventType pEventType, IEventListener pEventListener);


	public abstract boolean hasEventListener(IEventType pEventType);


	public abstract void removeEventListener(IEventType pEventType);


	public abstract void dispatchEvent(IEvent pEvent);

}
