package ru.qixi.api.manager;

import java.lang.reflect.Field;
import java.util.Locale;

import android.content.Context;
import android.util.SparseArray;


public class StringDataManager {

	private static StringDataManager	instance;

	private SparseArray<String>			mResources;
	private Locale						mLocale;


	private StringDataManager() {
		mResources = new SparseArray<String>();
	}


	public static StringDataManager getInstance() {
		if (instance == null) instance = new StringDataManager();
		return instance;
	}


	public void init(Context pContext, Field[] pFields) {
		update(pContext, pFields, Locale.getDefault());
	}


	public void update(Context pContext, Field[] pFields, Locale pLocale) {
		if (mLocale == pLocale) return;
		mLocale = pLocale;
		final SparseArray<String> res = mResources;
		for (Field field : pFields) {
			int value = 0;
			try {
				value = field.getInt(null);
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			res.put(value, pContext.getString(value));
		}
	}


	public String getString(int id) {
		return mResources.get(id);
	}

}
