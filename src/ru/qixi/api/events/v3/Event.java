package ru.qixi.api.events.v3;

import android.util.SparseArray;


public class Event implements IEvent {

	protected final int				mType;
	protected EventPhase			mPhase;
	protected SparseArray<Object>	mParams;
	protected Object				mParam;


	public Event(int type) {
		mType = type;
		mPhase = EventPhase.BUBBLING;
	}


	public Event(int type, SparseArray<Object> pParams) {
		mType = type;
		mParams = pParams;
		mPhase = EventPhase.BUBBLING;
	}


	public Event(int type, Object pParam) {
		mType = type;
		mParam = pParam;
		mPhase = EventPhase.BUBBLING;
	}


	@Override
	public int getType() {
		return mType;
	}


	@Override
	public EventPhase getPhase() {
		return mPhase;
	}


	@Override
	public void setPhase(EventPhase pEventPhase) {
		mPhase = pEventPhase;
	}


	@Override
	public Object getParameter() {
		return mParam;
	}


	@Override
	public Object getParameter(int id) {
		if (mParams != null) { return mParams.get(id); }
		return null;
	}


	public SparseArray<Object> getParameters() {
		return mParams;
	}


	public String toString() {
		return String.format("{Type: %s, Phase: %s, Params: %s}", new Object[] { mType, mPhase, mParams != null ? mParams.toString() : "none" });
	}

}
