package ru.qixi.api.events.v3;

public interface IEventDispatcher {

	void addEventListener(int pEventType, IEventListener pEventListener);


	void addEventListener(int pEventType, IEventListener pEventListener, boolean pUseCapture);


	boolean hasEventListener(int pEventType);


	boolean hasEventListener(int pEventType, boolean pUseCapture);


	void removeEventListener(int pEventType);


	void removeEventListener(int pEventType, boolean pUseCapture);


	void dispatchEvent(IEvent pEvent);


	void registerClient(IEventDispatcher pEventDispatcher);


	void unregisterClient(IEventDispatcher pEventDispatcher);


	void dispatchCaptureEvent(IEvent pEvent);

}
