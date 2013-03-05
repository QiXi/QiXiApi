package ru.qixi.api.audio;

public abstract class BaseAudio implements IAudio{

	private final IAudioManager	mAudioManager;
	
	protected float				mLeftVolume		= 1.0f;
	protected float				mRightVolume	= 1.0f;
	
	protected boolean mReleased;


	public BaseAudio(final IAudioManager pAudioManager) {
		mAudioManager = pAudioManager;
	}


	protected IAudioManager getAudioManager() {

		return mAudioManager;
	}
}
