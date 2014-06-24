package ru.qixi.api.statemachine;

public class BaseState extends State {

	public BaseState(String pTag) {
		super(pTag);
	}


	@Override
	public void enterState() {
		System.out.println("enterState: " + this);
	}


	@Override
	public void leaveState() {
		System.out.println("leaveState: " + this);
	}


	@Override
	public void updateState(float time) {
		System.out.println("updateState: " + this);
	}

}
