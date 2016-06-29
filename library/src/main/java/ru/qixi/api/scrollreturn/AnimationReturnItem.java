package ru.qixi.api.scrollreturn;

import android.view.View;
import android.view.animation.TranslateAnimation;

import ru.qixi.api.scrollreturn.BaseScrollReturn.BaseReturnItem;
import ru.qixi.api.scrollreturn.BaseScrollReturn.ReturnDirection;
import ru.qixi.api.scrollreturn.BaseScrollReturn.ReturnState;

public class AnimationReturnItem extends BaseReturnItem {

	public AnimationReturnItem(ReturnDirection pDirection) {
		super(pDirection);
	}


	@Override
	public void reset() {
		if (offset != 0) {
			runAnimation(view, offset * direction.direction, 0);
		}
		super.reset();
	}


	@Override
	public void update(int scrollY) {
		scrollY*=100;
		final int MIN = minPosition;
		final int MAX = 0;
		final ReturnState oldState = state;
		final int currentY = offset;
		int newOffset = offset;

		switch (state) {
		case OFF_SCREEN:
			newOffset = getNewOffset(scrollY, MIN, MAX);
			if (newOffset == MIN) {
				setState(ReturnState.OFF_SCREEN);
			}
			else if (newOffset == MAX) {
				setState(ReturnState.ON_SCREEN);
			}
			break;
		case ON_SCREEN:
		case RETURNING:
			newOffset = getNewOffset(scrollY, MIN, MAX);
			if (newOffset <= MIN) {
				setState(ReturnState.OFF_SCREEN);
			}
			break;
		}
		if (oldState != state) {
			offset = newOffset;
			runAnimation(view, currentY * direction.direction, offset * direction.direction);
			if (listener != null) {
				listener.onChengeState(state);
			}
		}
	}


	private void runAnimation(final View view, int currentY, int translationY) {
		if (view == null) {
			return;
		}
		TranslateAnimation animation = new TranslateAnimation(0, 0, currentY, translationY);
		animation.setFillAfter(true);
		animation.setDuration(400);
		view.startAnimation(animation);
	}

}
