package ru.qixi.android.events;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public interface IDispatcher {

	void dispatchEvent(final Event<?> pEvent);


	void setDispatcher(final EventDispatcher pDispatcher);

}
