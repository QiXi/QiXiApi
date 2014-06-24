package ru.qixi.api.statemachine;

import java.util.ArrayList;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class StateManager {

	protected ArrayList<State>	mStack;
	protected int				mLength;


	public StateManager() {
		mStack = new ArrayList<State>();
		mLength = 0;
	}


	public void pushState(final State pState) {
		if (mLength != 0) {
			if (mStack.get(mLength - 1) == pState) return;
			pState.previous = mStack.get(mLength - 1);
			pState.previous.leaveState();
		}
		mStack.add(pState);
		mLength = mStack.size();
		pState.enterState();
	}


	public void popState() {
		if (mLength != 0) {
			State state = mStack.remove(mLength - 1);
			state.leaveState();
			state.previous = null;
			mLength = mStack.size();
			if (mLength != 0) mStack.get(mLength - 1).enterState();
		}
	}


	public void changeState(final State pState) {
		if (mLength != 0) mStack.get(mLength - 1).leaveState();
		for (int i = 0; i < mLength; i++) {
			mStack.get(i).previous = null;
		}
		mStack = new ArrayList<State>();
		mLength = mStack.size();
		pushState(pState);
	}


	public State getState() {
		if (mLength != 0) {
			return mStack.get(mLength - 1);
		}
		return null;
	}


	public void updatePreviousState(final float pTime) {
		if (mLength > 1) {
			State state = mStack.get(mLength - 2);
			state.updateState(pTime);
		}
	}


	public int getSize() {
		return mLength;
	}


	public void update(final float pDeltaTime) {
		if (mLength != 0) mStack.get(mLength - 1).updateState(pDeltaTime);
	}


	public void clear() {
		for (int i = 0; i < mLength; i++) {
			mStack.get(i).previous = null;
		}
		mStack = new ArrayList<State>();
		mLength = mStack.size();
	}

}
