package ru.qixi.api.audio.sound;

import ru.qixi.api.audio.IAudio;
import ru.qixi.api.audio.IAudioManager;
import android.media.AudioManager;


public class BaseSoundManager<T extends IAudio> implements IAudioManager<IAudio> {

	private AudioManager	mAudioManager;


	@Override
	public float getMasterVolume() {
		return 0;
	}


	@Override
	public void setMasterVolume(float pMasterVolume) {

	}

}
