package ru.qixi.api.events;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public interface IHandler {

	void onEvent(final Event<?> pEvent);

}
