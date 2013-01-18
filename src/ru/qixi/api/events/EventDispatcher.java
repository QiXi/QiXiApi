package ru.qixi.android.events;


import java.util.HashMap;

import android.util.Log;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class EventDispatcher{
	
	private static EventDispatcher rootDispatcher;

	private HashMap<String, IHandler> listeners;
	private EventDispatcher parentDispatcher;
	private String className;


	public EventDispatcher() {
		this(null, "root");
	}


	public EventDispatcher(final EventDispatcher pDispatcher) {
		this(pDispatcher, null);
	}


	public EventDispatcher(final EventDispatcher pDispatcher, final String pClassName) {
		if (pDispatcher != null) {
			parentDispatcher = pDispatcher;
		} else if (rootDispatcher != null) {
			Log.e("EventDispatcher", "rootDispatcher!=null");
		} else {
			rootDispatcher = this;
		}
		
		listeners = new HashMap<String, IHandler>();
		className = pClassName;
	}


	public void addEventListener(final String pName, final IHandler pListener) {
		listeners.put(pName, pListener);
	}


	public void removeEventListener(final String pName) {
		listeners.remove(pName);
	}


	public void dispatchEvent(final Event<?> pEvent) {
		final String type = pEvent.type;
		if (listeners.containsKey(type)) {
			final IHandler handler = listeners.get(type);
			handler.onEvent(pEvent);
		}
		if (parentDispatcher != null) {
			parentDispatcher.dispatchEvent(pEvent);
		}
	}

	
	public void dispatchEventToRoot(final Event<?> pEvent) {
		//if (rootDispatcher != null) {
			rootDispatcher.dispatchEvent(pEvent);
		//}
	}
	

	public String getClassName() {
		return className;
	}
    
}
