package ru.qixi.api.audio.sound;

import ru.qixi.api.audio.BaseAudio;
import android.media.SoundPool;


public class Sound extends BaseAudio {

	private int		mSoundID;
	private int		mStreamID;
	private boolean	mLoaded;


	Sound(final SoundManager pSoundManager, final int pSoundID) {
		super(pSoundManager);
		mSoundID = pSoundID;
	}


	@Override
	protected SoundManager getAudioManager() {
		return (SoundManager) super.getAudioManager();
	}


	private SoundPool getSoundPool() {
		return getAudioManager().getSoundPool();
	}


	public int getSoundID() {
		return mSoundID;
	}


	public void setSoundID(final int pSoundID) {
		mSoundID = pSoundID;
	}


	public int getStreamID() {
		return mStreamID;
	}


	public boolean isLoaded() {
		return mLoaded;
	}


	public void setLoaded(final boolean pLoaded) {
		mLoaded = pLoaded;
	}


	@Override
	public void play() {
		final float masterVolume = getMasterVolume();
		mStreamID = getSoundPool().play(mSoundID, mLeftVolume * masterVolume, mRightVolume * masterVolume, SoundManager.PRIORITY_LOW, 0, 1.0f);
	}


	@Override
	public void stop() {
		if (mStreamID != 0) {
			getSoundPool().stop(mStreamID);
		}
	}


	@Override
	public void resume() {
		if (mStreamID != 0) {
			getSoundPool().resume(mStreamID);
		}
	}


	@Override
	public void pause() {
		if (mStreamID != 0) {
			getSoundPool().pause(mStreamID);
		}
	}


	@Override
	public void release() {
		if (mReleased) {
			return;
		}

		getSoundPool().unload(mSoundID);
		mSoundID = 0;
		mLoaded = false;

		getAudioManager().remove(this);

		mReleased = true;
	}

}
