package ru.qixi.api.statemachine;


import java.util.ArrayList;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/


public class StateManager {
	
	protected ArrayList<State> stack;
	protected int length;
	
	
	public StateManager()
	{
		stack = new ArrayList<State>();
		length = 0;
	}
	
	
	public void pushState(State state)
	{
		if(length != 0){
			if(stack.get(length-1) == state)
				return;
			state.previous = stack.get(length-1);
			state.previous.leaveState();
		}
		stack.add(state);
		length = stack.size();
		state.enterState();
	}
	
	
	public void popState()
	{
		if(length!=0){
			State state = stack.remove(length-1);			
			state.leaveState();
			state.previous = null;	
			
			length = stack.size();			
			if(length!=0)
				stack.get(length-1).enterState();
		}
	}
	
	
	public void changeState(State state)
	{	
		if(length!=0)
			stack.get(length-1).leaveState();
		for (int i = 0; i < length; i++) {
			stack.get(i).previous = null;
		}				
		stack = new ArrayList<State>();
		length = stack.size();
		pushState(state);
	}
	
	
	public State getState()
	{
		if(length!=0){
			return stack.get(length-1);
		}
		return null;
	}
	
	
	public void updatePreviousState(float time)
	{
		State state = stack.get(length-2);
		state.updateState(time);
	}
	
	
	public int getSize()
	{
		return length;
	}
	
	
	public void update(float deltaTime)
	{
		if(length!=0)
			stack.get(length-1).updateState(deltaTime);
	}
	
	
	public void clear()
	{		
		for (int i = 0; i < length; i++) {
			stack.get(i).previous = null;
		}		
		stack = new ArrayList<State>();
		length = stack.size();		
	}		
	
	
}
