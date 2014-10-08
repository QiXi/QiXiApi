package ru.qixi.api.font;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Typeface;


public final class FontsOverride {

	public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
		final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), fontAssetName);
		replaceFont(staticTypefaceFieldName, customFontTypeface);
	}


	protected static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
		try {
			final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
			staticField.setAccessible(true);
			staticField.set(null, newTypeface);
		}
		catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
