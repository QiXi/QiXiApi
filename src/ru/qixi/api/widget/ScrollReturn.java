package ru.qixi.api.widget;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ScrollReturn {

	private ListView				mListView;
	private ReturnMode				mMode;
	private ReturnItem				mTop;
	private ReturnItem				mBottom;

	private OnScrollListener		mOnScrollListener;
	private OnScrollListener		mInternalOnScrollListener;
	private OnGlobalLayoutListener	mOnGlobalLayoutListener;


	public ScrollReturn(ReturnMode pMode) {
		mMode = pMode;
		mTop = new ReturnItem(ReturnDirection.TOP);
		mBottom = new ReturnItem(ReturnDirection.BOTTOM);

		mInternalOnScrollListener = new OnScrollListener() {

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if(totalItemCount == 0 || mListView == null)
					return;

				View v = mListView.getChildAt(0);
				if(v == null)
					return;
				int top = v.getTop();
				int height = v.getHeight();
				mTop.update(top, height, firstVisibleItem);
				mBottom.update(top, height, firstVisibleItem);
				if(mOnScrollListener != null)
					mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
			}


			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(mOnScrollListener != null)
					mOnScrollListener.onScrollStateChanged(view, scrollState);
			}
		};

		mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				mTop.updateParams();
				mBottom.updateParams();
			}
		};
	}


	public void setReturnView(ReturnDirection pReturnDirection, View pView) {
		if(pReturnDirection == ReturnDirection.TOP){
			mTop.init(pView);
		}
		else if(pReturnDirection == ReturnDirection.BOTTOM){
			mBottom.init(pView);
		}
	}


	public void setup(ListView pListView, OnScrollListener listener) {
		mListView = pListView;
		mListView.setOnScrollListener(mInternalOnScrollListener);
		mListView.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
		mOnScrollListener = listener;
	}


	public void setOnScrollListener(OnScrollListener listener) {
		mOnScrollListener = listener;
	}


	public void reset() {
		mTop.reset();
		mBottom.reset();
	}

	private class ReturnItem {

		View				view;
		ReturnState			state;
		ReturnDirection		direction;
		int					minPosition;
		int					height;
		int					offset;
		int					lastTop;
		int					lastVisibleItem;
		int					lastHeight;
		TranslateAnimation	animation;


		ReturnItem(ReturnDirection pDirection) {
			direction = pDirection;
			state = ReturnState.ON_SCREEN;
		}


		void init(View pView) {
			view = pView;
			updateParams();
		}


		void update(int top, int height, int firstVisibleItem) {
			int scrollY;

			if(lastVisibleItem != firstVisibleItem){
				int dif = firstVisibleItem - lastVisibleItem;
				if(dif > 0){
					scrollY = -(lastHeight + lastTop) + top;
				}
				else{
					scrollY = -(lastTop - (height + top));
				}
			}
			else{
				scrollY = top - lastTop;
			}

			lastTop = top;
			lastHeight = height;
			lastVisibleItem = firstVisibleItem;

			int translationY = 0;

			switch(state){
			case OFF_SCREEN:
				updateOffset(scrollY);
				if(offset > minPosition){
					state = ReturnState.RETURNING;
				}
				translationY = offset;
				break;

			case ON_SCREEN:
				updateOffset(scrollY);
				if(offset <= minPosition){
					state = ReturnState.OFF_SCREEN;
				}
				translationY = offset;
				break;

			case RETURNING:
				updateOffset(scrollY);
				if(offset <= minPosition){
					state = ReturnState.OFF_SCREEN;
				}
				else if(offset >= 0){
					state = ReturnState.ON_SCREEN;
				}
				translationY = offset;
				break;
			}
			translation(translationY * direction.direction);
		}


		void updateParams() {
			height = view.getHeight();
			minPosition = -height;
		}


		void updateOffset(int value) {
			offset += value;
			if(offset <= minPosition){
				offset = minPosition;
			}
			if(offset >= 0){
				offset = 0;
			}
		}


		@SuppressLint("NewApi")
		void translation(int translationY) {
			if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB){
				animation = new TranslateAnimation(0, 0, translationY, translationY);
				animation.setFillAfter(true);
				animation.setDuration(0);
				view.startAnimation(animation);
			}
			else{
				view.setTranslationY(translationY);
			}
		}


		void reset() {
			state = ReturnState.ON_SCREEN;
			translation(0);
			offset = 0;
		}
	}

	public enum ReturnDirection {
		TOP(1), BOTTOM(-1);

		int	direction;


		ReturnDirection(int pDirection) {
			direction = pDirection;
		}
	}

	private enum ReturnState {
		ON_SCREEN, OFF_SCREEN, RETURNING
	}

	public enum ReturnMode {
		TOP, BOTTOM, BOTH
	}

}