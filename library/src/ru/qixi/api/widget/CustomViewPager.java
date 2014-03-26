package ru.qixi.api.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomViewPager extends ViewPager {

	private boolean	mEnabled;


	public CustomViewPager(Context context) {
		super(context);
		mEnabled = false;
	}


	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mEnabled = false;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mEnabled) { return super.onTouchEvent(event); }
		return false;
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (mEnabled) { return super.onInterceptTouchEvent(event); }
		return false;
	}


	public void setPagingEnabled(boolean enabled) {
		mEnabled = enabled;
	}
}
