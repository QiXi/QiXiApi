package ru.qixi.android.events;

 /**
 * @author QiXi qixi@mail.ru
 * 2011 - 14.11.2012
 * http://qixi.ru
 **/

public interface IHandler {
	
	void onEvent(final Event<?> pEvent);

}
