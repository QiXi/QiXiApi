package ru.qixi.api.events.v3;

public interface IEvent {

	public int getType();


	public EventPhase getPhase();


	public void setPhase(EventPhase pEventPhase);


	public Object getParameter();


	public Object getParameter(int pParam);

}
