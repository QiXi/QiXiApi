package ru.qixi.api.audio;

public interface IAudioManager<T extends IAudio> {

	public float getMasterVolume();


	public void setMasterVolume(final float pMasterVolume);

}
