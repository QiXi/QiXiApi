package ru.qixi.api.events.v3;

import android.util.SparseArray;

public interface IEvent {

	int getType();


	EventPhase getPhase();


	void setPhase(EventPhase pEventPhase);


	Object getParameter();


	Object getParameter(int pParam);


	SparseArray<Object> getParameters();


	void onFinish();

}
