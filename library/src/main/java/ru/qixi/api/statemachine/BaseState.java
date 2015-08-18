package ru.qixi.api.statemachine;

import ru.qixi.api.util.Log;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class BaseState extends State {

	public BaseState(String pTag) {
		super(pTag);
	}


	@Override
	public void enterState() {
		Log.it(StateManager.TAG, "enterState:[%s]", this);
	}


	@Override
	public void leaveState() {
		Log.it(StateManager.TAG, "leaveState:[%s]", this);
	}


	@Override
	public void updateState(float time) {
		Log.it(StateManager.TAG, "updateState:[%s]", this);
	}

}
