package ru.qixi.api.util.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class TouchableSpan extends ClickableSpan {

	private boolean	mIsPressed;
	private int		mNormalTextColor;
	private int		mPressedTextColor;
	private int		mPressedBackgroundColor;


	public void setColorParams(int pNormalTextColor, int pPressedTextColor, int pPressedBackgroundColor) {
		mNormalTextColor = pNormalTextColor;
		mPressedTextColor = pPressedTextColor;
		mPressedBackgroundColor = pPressedBackgroundColor;
	}


	public void setPressed(boolean pSelected) {
		mIsPressed = pSelected;
	}


	@Override
	public void updateDrawState(TextPaint pTextPaint) {
		super.updateDrawState(pTextPaint);
		pTextPaint.setColor(mIsPressed ? mPressedTextColor : mNormalTextColor);
		pTextPaint.bgColor = mIsPressed ? mPressedBackgroundColor : 0x00000000;
		pTextPaint.setUnderlineText(false);
	}

}