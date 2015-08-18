package ru.qixi.api.helper;

import android.util.FloatMath;


public class PositionHelper {
	
	private float mWidth;
	private float mHeight;
	
	private float mItemSize;
	private float mItemSizeHalf;
	private int mCount;
	
	private int mItemInWidth;
	private int mItemInHeight;
	
	private float mSpaceInWidth;
	private float mSpaceInHeight;
	
	private float mSpaceInWidthHalf;
	private float mSpaceInHeightHalf;
	
	private float mCenterX[];
	private float mCenterY[];
	
	private float mContentWidth;
	private float mContentHeight;
	
	private boolean mIsHorizontal = false;
	
	
	public PositionHelper(final float pWidth, final float pHeight, final float pItemSize, final int pCount){
		
		mWidth = pWidth;
		mHeight = pHeight;
		mItemSize = pItemSize;
		mCount = pCount;
		
		mItemSizeHalf = pItemSize/2;
		
		mItemInWidth = (int) FloatMath.floor(mWidth / mItemSize);
		mItemInHeight = (int) FloatMath.floor(mHeight / mItemSize);

		mSpaceInWidth = mWidth - (mItemInWidth * mItemSize);
		mSpaceInHeight = mHeight - (mItemInHeight * mItemSize);
		mSpaceInWidthHalf = mSpaceInWidth/2;
		mSpaceInHeightHalf = mSpaceInHeight/2;
		
		if(mIsHorizontal){
			mItemInWidth = (int) FloatMath.ceil((float)mCount/mItemInHeight);
		}else{
			mItemInHeight = (int) FloatMath.ceil((float)mCount/mItemInWidth);
		}
		
		mCenterX = new float[mCount];
		mCenterY = new float[mCount];
		
		mContentWidth = mItemInWidth*pItemSize;
		mContentHeight = mItemInHeight*pItemSize;
		
		update();
	}
	
	
	public void setSize(final float pWidth, final float pHeight) {
		mWidth = pWidth;
		mHeight = pHeight;
		
		update();
	}


	private void update() {
		float centerX = 0;
		float centerY = 0;
		int item = 0;
		
		for (int i = 0; i < mItemInHeight; i++) {
			centerY = i*mItemSize+mItemSizeHalf+mSpaceInHeightHalf;
			
			for (int j = 0; j < mItemInWidth; j++) {
				centerX = j*mItemSize+mItemSizeHalf+mSpaceInWidthHalf;
				mCenterX[item] = centerX;
				mCenterY[item] = centerY;
				item++;
				if (item>=mCount){
					return;
				}
			}
		}
	}
	
	
	public float getCenterX(final int item){
		return mCenterX[item];
	}
	
	
	public float getCenterY(final int item){
		return mCenterY[item];
	}
	
		
	public float getContentWidth(){
		return mContentWidth;
	}
	
	
	public float getContentHeight(){
		return mContentHeight;
	}


}
