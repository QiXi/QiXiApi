package ru.qixi.api.events.v3;

public interface IEvent {

	int getType();


	IEventPhase getPhase();


	void setPhase(IEventPhase pEventPhase);


	Object getParameter();


	Object getParameter(int pParam);


	void onFinish();


	void onFeedback();

}
