package ru.qixi.api.scrollreturn;

import android.view.View;

public class BaseScrollReturn {

	protected IReturnItem	mTop;
	protected IReturnItem	mBottom;


	public BaseScrollReturn() {
		mTop = new BaseReturnItem(ReturnDirection.TOP);
		mBottom = new BaseReturnItem(ReturnDirection.BOTTOM);
	}


	public void reset() {
		mTop.reset();
		mBottom.reset();
	}


	public void setupParams() {
		mTop.updateParams();
		mBottom.updateParams();
	}


	public void setReturnView(ReturnDirection pReturnDirection, View pView, ReturnItemListener pListener) {
		switch (pReturnDirection) {
		case TOP:
			mTop.init(pView, pListener);
			break;
		case BOTTOM:
			mBottom.init(pView, pListener);
			break;
		}
	}


	public void addReturnItem(IReturnItem pItem) {
		switch (pItem.getReturnDirection()) {
		case TOP:
			mTop = pItem;
			break;
		case BOTTOM:
			mBottom = pItem;
			break;
		}
	}


	public void onScrollChanged(int pDif) {
		mTop.update(pDif);
		mBottom.update(pDif);
	}


	//BaseReturnItem
	public static class BaseReturnItem implements IReturnItem {

		protected ReturnState			state;
		protected ReturnDirection		direction;
		protected ReturnItemListener	listener;
		protected View					view;
		protected int					offset;
		protected int					minPosition;


		protected BaseReturnItem(ReturnDirection pDirection) {
			direction = pDirection;
			setState(ReturnState.ON_SCREEN);
		}


		@Override
		public void reset() {
			setState(ReturnState.ON_SCREEN);
			offset = 0;
		}


		@Override
		public void init(View pView, ReturnItemListener pListener) {
			view = pView;
			listener = pListener;
			updateParams();
		}


		@Override
		public void updateParams() {
			if (view == null) {
				return;
			}
			int height = view.getHeight();
			minPosition = -height;
		}


		@Override
		public void update(int pDif) {

		}


		@Override
		public void setState(ReturnState pState) {
			if (state == pState) {
				return;
			}
			state = pState;
		}


		protected int getNewOffset(int value, int min, int max) {
			int newOffset = offset + value;
			if (newOffset < min) {
				newOffset = min;
			}
			if (newOffset > max) {
				newOffset = max;
			}
			return newOffset;
		}


		@Override
		public ReturnDirection getReturnDirection() {
			return direction;
		}

	}


	//IReturnItem
	public static interface IReturnItem {

		void reset();


		void init(View pView, ReturnItemListener pListener);


		void updateParams();


		void update(int dif);


		void setState(ReturnState pState);


		ReturnDirection getReturnDirection();

	}


	//ReturnState
	public static enum ReturnState {
		ON_SCREEN,
		OFF_SCREEN,
		RETURNING
	}


	//ReturnItemListener
	public static interface ReturnItemListener {

		void onChengeState(ReturnState state);
	}


	//ReturnDirection
	public static enum ReturnDirection {
		TOP(1),
		BOTTOM(-1);

		int	direction;


		ReturnDirection(int pDirection) {
			direction = pDirection;
		}
	}

}
