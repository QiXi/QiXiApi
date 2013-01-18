package ru.qixi.api.events;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/



public abstract class Event<T> implements IEvent<T>{

	//public static final String TRACE = "trace";

	protected String type;
	protected T target;


	/*public Event() {
		this(TRACE);
	}


	public Event(final String pType) {
		this(pType, null);
	}


	public Event(final String pType, final T pTarget) {
		type = pType;
		target = pTarget;
	}*/


	public String getType() {
		return type;
	}


	public T getTarget() {
		return target;
	}


	@Override
	public String toString() {
		if (target != null) {
			return "[type=" + type + " target=" + target.getClass().getSimpleName() + "]";
		} else {
			return "[type=" + type + "]";
		}
	}

}
