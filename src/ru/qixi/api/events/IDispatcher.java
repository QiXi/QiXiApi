package ru.qixi.android.events;


 /**
 * @author QiXi qixi@mail.ru
 * 15.09.2011 - 14.11.2012
 * http://qixi.ru
 **/


public interface IDispatcher {
	
	void dispatchEvent(final Event<?> pEvent);
	void setDispatcher(final EventDispatcher pDispatcher);

}
