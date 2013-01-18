package ru.qixi.android.io;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public interface IProgress{

	public static final int PROGRESS_MIN = 0;
	public static final int PROGRESS_MAX = 100;


	public void onProgressChanged(final int pProgress);

}
