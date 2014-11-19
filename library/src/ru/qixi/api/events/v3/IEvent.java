package ru.qixi.api.events.v3;

public interface IEvent {

	int getType();


	EventPhase getPhase();


	void setPhase(EventPhase pEventPhase);


	Object getParameter();


	Object getParameter(int pParam);


	void onFinish();


	void onFeedback();

}
