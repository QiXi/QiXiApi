package ru.qixi.api.statemachine;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public abstract class State {

	final String	mTag;
	State			previous;


	public State(final String pTag) {
		mTag = pTag;
	}


	public String getTag() {
		return mTag;
	}


	abstract void enterState();


	abstract void leaveState();


	abstract void updateState(float time);


	@Override
	public String toString() {
		return "tag:" + mTag + " previous:" + previous;
	}

}
