package ru.qixi.api.events.v3;

public class Event implements IEvent {

	protected int			mType;
	protected IEventPhase	mPhase;
	protected Object		mParam;


	public Event(int pType) {
		mType = pType;
		mPhase = IEventPhase.BUBBLING;
	}


	@Override
	public int getType() {
		return mType;
	}


	public void setType(int type) {
		mType = type;
	}


	@Override
	public IEventPhase getPhase() {
		return mPhase;
	}


	@Override
	public void setPhase(IEventPhase pEventPhase) {
		mPhase = pEventPhase;
	}


	@Override
	public Object getParameter(int pId) {
		if (mParam != null && mParam instanceof IParam) {
			return ((IParam) mParam).get(pId);
		}
		return null;
	}


	@Override
	public Object getParameter() {
		return mParam;
	}


	public void setParameter(Object pParam) {
		mParam = pParam;
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
