package ru.qixi.api.font;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;


public class FontManager {

	private static FontManager			instance;

	private HashMap<String, Typeface>	mFontMap;


	public FontManager() {
		mFontMap = new HashMap<String, Typeface>();
	}


	public static FontManager getInstance() {
		if (instance == null) {
			return instance = new FontManager();
		} else {
			return instance;
		}
	}


	public Typeface getFont(Context pContext, String pFont) {
		Typeface typeface = mFontMap.get(pFont);
		if (typeface == null) {
			try {
				typeface = Typeface.createFromAsset(pContext.getResources().getAssets(), "fonts/" + pFont);
				mFontMap.put(pFont, typeface);
			}
			catch (Exception e) {
				Log.e("FontFactory", "Could not get typeface: " + e.getMessage() + " with name: " + pFont);
				return null;
			}

		}
		return typeface;
	}

}
