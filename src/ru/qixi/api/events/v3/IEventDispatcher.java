package ru.qixi.api.events.v3;

public interface IEventDispatcher {

	public void addEventListener(int pEventType, IEventListener pEventListener);


	public void addEventListener(int pEventType, IEventListener pEventListener, boolean pUseCapture);


	public boolean hasEventListener(int pEventType);


	public boolean hasEventListener(int pEventType, boolean pUseCapture);


	public void removeEventListener(int pEventType);


	public void removeEventListener(int pEventType, boolean pUseCapture);


	public void dispatchEvent(IEvent pEvent);


	public void registerClient(IEventDispatcher pEventDispatcher);


	public void unregisterClient(IEventDispatcher pEventDispatcher);


	public void dispatchCaptureEvent(IEvent pEvent);

}
