package ru.qixi.api.events.v3;

import android.util.SparseArray;


public class Event implements IEvent {

	protected int					mType;
	protected EventPhase			mPhase;
	protected SparseArray<Object>	mParams;
	protected Object				mParam;


	public Event(int pType) {
		set(pType);
	}


	public Event(int pType, SparseArray<Object> pParams) {
		set(pType, pParams);
	}


	public Event(int pType, Object pParam) {
		set(pType, pParam);
	}


	public void set(int pType) {
		mType = pType;
		mPhase = EventPhase.BUBBLING;
	}


	public void set(int pType, SparseArray<Object> pParams) {
		mType = pType;
		mParams = pParams;
		mPhase = EventPhase.BUBBLING;
	}


	public void set(int pType, Object pParam) {
		mType = pType;
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
	public Object getParameter(int pId) {
		if (mParams != null) {
			return mParams.get(pId);
		}
		return null;
	}


	@Override
	public SparseArray<Object> getParameters() {
		return mParams;
	}


	@Override
	public void onFinish() {

	}


	@Override
	public void onFeedback() {
		// empty
	}


	public String toString() {
		return String.format("{Type: %s, Phase: %s, Params: %s}", new Object[] { mType, mPhase, mParams != null ? mParams.toString() : "none" });
	}

}
