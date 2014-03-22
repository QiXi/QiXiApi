package ru.qixi.api.events.v2;

public interface IEvent {

	public IEventType getType();


	public Object getParameter(IEventParam pParam);

}
