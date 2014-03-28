package ru.qixi.volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;


public class DiskBitmapCache extends DiskBasedCache implements ImageCache {

	private final File				mRootDirectory;
	private final BitmapLruCache	mMemoryCache;


	public DiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes) {
		super(rootDirectory, maxCacheSizeInBytes);
		mRootDirectory = rootDirectory;
		mMemoryCache = new BitmapLruCache(BitmapLruCache.getDefaultLruCacheSize());
	}


	@Override
	public Bitmap getBitmap(String url) {
		Bitmap bitmap = mMemoryCache.get(url);
		if (bitmap != null) { return bitmap; }

		final Entry entry = get(url);
		if (entry == null) return null;
		bitmap = BitmapFactory.decodeByteArray(entry.data, 0, entry.data.length);

		mMemoryCache.putBitmap(url, bitmap);
		return bitmap;
	}


	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		final Entry entry = new Entry();

		if (mMemoryCache.get(url) == null) {
			mMemoryCache.put(url, bitmap);
		}

		entry.data = convertBitmapToBytes(bitmap);
		put(url, entry);
	}


	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private static byte[] convertBitmapToBytes(Bitmap bitmap) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			ByteBuffer buffer = ByteBuffer.allocate(bitmap.getByteCount());
			bitmap.copyPixelsToBuffer(buffer);
			return buffer.array();
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 95, baos);
			return baos.toByteArray();
		}
	}

}
