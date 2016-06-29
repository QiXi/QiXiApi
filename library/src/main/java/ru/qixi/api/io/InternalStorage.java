package ru.qixi.api.io;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/

public class InternalStorage {

	public static String getAbsolutePath(final Context pContext, final String pFilePath) {
		return pContext.getFilesDir().getAbsolutePath() + pFilePath;
	}


	public static boolean isFileExisting(final Context pContext, final String pFilePath) {
		final String absoluteFilePath = getAbsolutePath(pContext, pFilePath);
		final File file = new File(absoluteFilePath);
		return file.exists() && file.isFile();
	}


	public static boolean isDirectoryExisting(final Context pContext, final String pDirectory) {
		final String absoluteDirectoryPath = getAbsolutePath(pContext, pDirectory);
		final File file = new File(absoluteDirectoryPath);
		return file.exists() && file.isDirectory();
	}


	public static boolean ensureDirectoriesExist(final Context pContext, final String pDirectory) {
		if (isDirectoryExisting(pContext, pDirectory)) {
			return true;
		} 
		final String absoluteDirectoryPath = getAbsolutePath(pContext, pDirectory);
		return new File(absoluteDirectoryPath).mkdirs();
	}


	public static boolean copy(final Context pContext, final int pSourceResourceID, final String pFilePath, final IStreamProgress pProgress) throws FileNotFoundException {
		return copy(pContext, pContext.getResources().openRawResource(pSourceResourceID), pFilePath, pProgress);
	}


	public static boolean copy(final Context pContext, final String pSourceAssetPath, final String pFilePath, final IStreamProgress pProgress) throws IOException {
		return copy(pContext, pContext.getAssets().open(pSourceAssetPath), pFilePath, pProgress);
	}


	public static boolean copy(final Context pContext, final InputStream pInputStream, final String pFilePath, final IStreamProgress pProgress) throws FileNotFoundException {
		return StreamUtils.copyInProgerssAndClose(pInputStream, new FileOutputStream(new File(pContext.getFilesDir() + pFilePath)), pProgress);
	}

}
