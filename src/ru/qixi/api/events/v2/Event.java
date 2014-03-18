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
		Object param = null;

		if (mParams != null) {
			param = mParams.get(id);
		}
		return param;
	}


	public String toString() {
		return String.format("{ %s, Params: %s }", new Object[] { mType, mParams != null ? mParams.keySet() : "none" });
	}
}
