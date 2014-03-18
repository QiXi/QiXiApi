package ru.qixi.api.events.v2;

import java.util.Map;


public abstract class CommitEvent extends Event {

	public CommitEvent(IEventType type) {
		super(type);
	}


	public CommitEvent(IEventType type, Map<IEventParam, Object> params) {
		super(type, params);
	}


	public Map<IEventParam, Object> getParameters() {
		return mParams;
	}


	public abstract void commit();

}
