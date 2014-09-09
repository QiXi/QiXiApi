package ru.qixi.api.events.v3;

public class Event implements IEvent {

	protected int			mType;
	protected EventPhase	mPhase;
	protected IParam		mParam;


	public Event(int pType) {
		set(pType);
	}


	public Event(int pType, IParam pParam) {
		set(pType, pParam);
	}


	public void set(int pType) {
		mType = pType;
		mPhase = EventPhase.BUBBLING;
	}


	public void set(int pType, IParam pParam) {
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
	public Object getParameter(int pId) {
		if (mParam != null) {
			return mParam.get(pId);
		}
		return null;
	}


	@Override
	public Object getParameters() {
		return mParam;
	}


	@Override
	public void onFinish() {

	}


	@Override
	public void onFeedback() {
		// empty
	}


	public String toString() {
		return String.format("{Type: %s, Phase: %s, Params: %s}",
				new Object[] { mType, mPhase, mParam != null ? mParam.toString() : "none" });
	}

}
