package ru.qixi.api.io;

import android.os.Environment;


/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class ExternalStorage {

	public static boolean isWriteable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}


	public static String getAbsolutePath(final String pFilePath) {
		return Environment.getExternalStorageDirectory() + "/" + pFilePath;
	}
	
	
}
