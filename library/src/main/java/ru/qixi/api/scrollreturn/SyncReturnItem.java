package ru.qixi.api.scrollreturn;

import ru.qixi.api.scrollreturn.BaseScrollReturn.BaseReturnItem;
import ru.qixi.api.scrollreturn.BaseScrollReturn.ReturnDirection;
import ru.qixi.api.scrollreturn.BaseScrollReturn.ReturnState;

public class SyncReturnItem extends BaseReturnItem {

	public SyncReturnItem(ReturnDirection pDirection) {
		super(pDirection);
	}


	@Override
	public void reset() {
		translation(0);
		super.reset();
	}


	public void update(int scrollY) {
		final int MIN = minPosition;
		final int MAX = 0;
		final ReturnState oldState = state;
		int newOffset = offset;

		switch (state) {
		case OFF_SCREEN:
			newOffset = getNewOffset(scrollY, MIN, MAX);
			if (newOffset > MIN) {
				setState(ReturnState.RETURNING);
			}
			break;
		case ON_SCREEN:
			newOffset = getNewOffset(scrollY, MIN, MAX);
			if (newOffset == MIN) {
				setState(ReturnState.OFF_SCREEN);
			}
			break;
		case RETURNING:
			newOffset = getNewOffset(scrollY, MIN, MAX);
			if (newOffset == MIN) {
				setState(ReturnState.OFF_SCREEN);
			}
			else if (newOffset == MAX) {
				setState(ReturnState.ON_SCREEN);
			}
			break;
		}
		offset = newOffset;
		translation(offset * direction.direction);
		if (oldState != state) {
			if (listener != null) {
				listener.onChengeState(state);
			}
		}
	}


	private void translation(int translationY) {
		if (view == null) {
			return;
		}
		view.setTranslationY(translationY);
	}

}
