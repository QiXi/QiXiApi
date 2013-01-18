package ru.qixi.android.io;

public interface IProgress{

	public static final int PROGRESS_MIN = 0;
	public static final int PROGRESS_MAX = 100;


	public void onProgressChanged(final int pProgress);

}
