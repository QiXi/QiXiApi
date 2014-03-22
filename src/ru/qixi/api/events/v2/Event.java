package ru.qixi.api.events.v2;

import java.util.Map;


public class Event implements IEvent {

	protected final IEventType					mType;
	protected final Map<IEventParam, Object>	mParams;


	public Event(IEventType type) {
		this(type, null);
	}


	public Event(IEventType type, Map<IEventParam, Object> params) {
		mType = type;
		mParams = params;
	}


	@Override
	public IEventType getType() {
		return mType;
	}


	@Override
	public Object getParameter(IEventParam id) {
		if (mParams != null) { return mParams.get(id); }
		return null;
	}


	public Map<IEventParam, Object> getParameters() {
		return mParams;
	}


	public String toString() {
		return String.format("{ %s, Params: %s }", new Object[] { mType, mParams != null ? mParams.keySet() : "none" });
	}
}
