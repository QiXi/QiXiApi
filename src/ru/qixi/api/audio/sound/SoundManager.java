package ru.qixi.api.audio.sound;

import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.SparseArray;


public class SoundManager extends BaseSoundManager implements OnLoadCompleteListener {

	private static final int			SOUND_STATUS_OK	= 0;

	private static final int			MAX_STREAMS		= 8;
	//private static final int			MAX_SOUNDS		= 32;

	public static final int				PRIORITY_LOW	= 0;
	public static final int				PRIORITY_NORMAL	= 1;
	public static final int				PRIORITY_HIGH	= 2;
	public static final int				PRIORITY_MUSIC	= 3;

	

	private SoundPool					mSoundPool;
	private final SparseArray<Sound>	mSounds;


	public SoundManager() {
		this(MAX_STREAMS);
	}


	public SoundManager(final int pMaxStreams) {
		mSoundPool = new SoundPool(pMaxStreams, AudioManager.STREAM_MUSIC, 0);
		mSoundPool.setOnLoadCompleteListener(this);

		mSounds = new SparseArray<Sound>();
	}


	SoundPool getSoundPool() {
		return mSoundPool;
	}


	@Override
	public synchronized void onLoadComplete(final SoundPool pSoundPool, final int pSoundID, final int pStatus) {
		if (pStatus == SOUND_STATUS_OK) {
			final Sound sound = mSounds.get(pSoundID);
			if (sound == null) {
				// throw new Exception("Unexpected soundID: '" + pSoundID +
				// "'.");
			} else {
				sound.setLoaded(true);
			}
		}
	}


	public void add(final Sound pSound) {
		mSounds.put(pSound.getSoundID(), pSound);
	}


	public void remove(final Sound pSound) {
		mSounds.remove(pSound.getSoundID());
	}

	
	public void releaseAll() {
		mSoundPool.release();
	}

	
	public void reset() {
		mSoundPool.release();
		mSounds.clear();
	}



}
