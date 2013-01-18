package ru.qixi.api.io;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/


public class StreamProgress implements IStreamProgress {

	protected boolean	mEnabled;
	protected long		mStreamProgress;
	protected long		mLength;
	protected int		mProgress;


	public StreamProgress() {
		mEnabled = true;
	}


	@Override
	public long getStreamLength() {
		return mLength;
	}


	@Override
	public void setStreamLength(final long pLength) {
		mLength = pLength;
	}


	@Override
	public boolean onStreamProgressChanged(final int pByte) {
		if (!mEnabled) {
			return false;
		}
		mStreamProgress += pByte;
		int progress = (int) (PROGRESS_MAX * (mStreamProgress * 1F / mLength));
		Math.min(PROGRESS_MAX, Math.max(PROGRESS_MIN, progress));
		onProgressChanged(progress);
		return true;
	}


	@Override
	public void onProgressChanged(int pProgress) {
		mProgress = pProgress;
	}


	@Override
	public void stopProgress() {
		mEnabled = false;
	}


	@Override
	public boolean isStopped() {
		return !mEnabled;
	}

}
