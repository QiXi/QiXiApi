package ru.qixi.api.events.v2;

public interface IEventDispatcher {

	public void addEventListener(IEventType pEventType, IEventListener pEventListener);


	public boolean hasEventListener(IEventType pEventType);


	public void removeEventListener(IEventType pEventType);


	public void dispatchEvent(IEvent pEvent);

}
