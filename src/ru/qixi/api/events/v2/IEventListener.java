package ru.qixi.api.events.v2;

public abstract interface IEventListener {

	public abstract void handleEvent(IEvent pEvent);
	
}
