package ru.qixi.api.events.v2;

public abstract interface IEvent {

	public abstract IEventType getType();


	public abstract Object getParameter(IEventParam pParam);

}
