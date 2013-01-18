package ru.qixi.android.events;

 /**
 * @author QiXi qixi@mail.ru
 * 14.11.2012 
 * http://qixi.ru
 **/


public interface IEvent<T> {

	String getType();


	T getTarget();

}
