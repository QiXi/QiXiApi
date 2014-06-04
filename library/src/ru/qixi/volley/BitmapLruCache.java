package ru.qixi.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;


public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageCache {

	public static int getDefaultLruCacheBytes() {
		final long maxMemory = Runtime.getRuntime().maxMemory();
		final int cacheSize = (int) (maxMemory / 8);
		return cacheSize;
	}


	public BitmapLruCache() {
		this(getDefaultLruCacheBytes());
	}


	public BitmapLruCache(int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}


	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}


	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}


	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}
