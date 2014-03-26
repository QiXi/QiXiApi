package ru.qixi.api.audio;

public abstract class BaseAudio implements IAudio {

	private final IAudioManager<? extends IAudio>	mAudioManager;

	protected float									mLeftVolume		= 1.0f;
	protected float									mRightVolume	= 1.0f;

	protected boolean								mReleased;


	public BaseAudio(final IAudioManager<? extends IAudio> pAudioManager) {
		mAudioManager = pAudioManager;
	}


	protected IAudioManager<? extends IAudio> getAudioManager() {

		return mAudioManager;
	}


	protected float getMasterVolume() {
		return mAudioManager.getMasterVolume();
	}

}
