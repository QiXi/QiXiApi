package ru.qixi.api.statemachine;

import java.util.ArrayList;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class StateManager {

	public final static String			TAG	= "StateManager";

	protected ArrayList<State>	mStack;
	protected int				mLength;


	public StateManager() {
		mStack = new ArrayList<>();
		mLength = 0;
	}


	public void pushState(State pState) {
		pushState(pState, false);
	}


	public void pushState(State pState, boolean pMissLeave) {
		if (mLength != 0) {
			if (mStack.get(mLength - 1) == pState)
				return;
			pState.previous = mStack.get(mLength - 1);
			if (!pMissLeave) {
				pState.previous.leaveState();
			}
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
			if (mLength != 0)
				mStack.get(mLength - 1).enterState();
		}
	}


	public void changeState(State pState) {
		if (mLength != 0) {
			State state = mStack.remove(mLength - 1);
			state.leaveState();
			state.previous = null;
			mLength = mStack.size();
			if (mLength != 0) {
				pState.previous = mStack.get(mLength - 1);
			}
		}
		mStack.add(pState);
		mLength = mStack.size();
		pState.enterState();
	}


	public State getState() {
		if (mLength != 0) {
			return mStack.get(mLength - 1);
		}
		return null;
	}


	public void updatePreviousState(float pTime) {
		if (mLength > 1) {
			State state = mStack.get(mLength - 2);
			state.updateState(pTime);
		}
	}


	public int getSize() {
		return mLength;
	}


	public void update(float pDeltaTime) {
		if (mLength != 0)
			mStack.get(mLength - 1).updateState(pDeltaTime);
	}


	public void clear() {
		for (int i = 0; i < mLength; i++) {
			mStack.get(i).previous = null;
		}
		mStack = new ArrayList<>();
		mLength = mStack.size();
	}

}
