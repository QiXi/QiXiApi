package ru.qixi.api.statemachine;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public abstract class State {

	protected State	previous;


	protected abstract void enterState();


	protected abstract void leaveState();


	protected abstract void updateState(float time);

}
