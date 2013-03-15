package ru.qixi.api.audio.sound;

import ru.qixi.api.audio.IAudio;
import ru.qixi.api.audio.IAudioManager;
import android.media.AudioManager;


public class BaseSoundManager<T extends IAudio> implements IAudioManager<IAudio> {

	private AudioManager	mAudioManager;
	protected float			mMasterVolume	= 1.0f;


	@Override
	public float getMasterVolume() {
		return mMasterVolume;
	}


	@Override
	public void setMasterVolume(float pMasterVolume) {
		mMasterVolume = pMasterVolume;
	}

}
