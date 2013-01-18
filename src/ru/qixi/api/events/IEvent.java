package ru.qixi.android.events;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public interface IEvent<T> {

	String getType();


	T getTarget();

}
