package ru.qixi.android.io;

public interface IStreamProgress extends IProgress {

	public long getStreamLength();


	public void setStreamLength(final long pLength);


	public boolean onStreamProgressChanged(final int pByte);


	public void stopProgress();


	public boolean isStopped();

}
