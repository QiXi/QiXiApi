package ru.qixi.api.events.v3;

import android.util.SparseArray;


public class Event implements IEvent {

	protected final int					mType;
	protected EventPhase				mPhase;
	protected final SparseArray<Object>	mParams;


	public Event(int type) {
		this(type, null);
	}


	public Event(int type, SparseArray<Object> params) {
		mType = type;
		mParams = params;
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
