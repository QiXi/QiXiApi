package ru.qixi.api.manager;

import android.content.Context;
import android.util.SparseArray;

import java.util.Locale;


public class StringDataManager {

    private static StringDataManager instance;

    private Context             mContext;
    private SparseArray<String> mResources;
    private Locale              mLocale;


    private StringDataManager() {
        mResources = new SparseArray<>(64);
    }


    public static StringDataManager getInstance() {
        if (instance == null) {
            instance = new StringDataManager();
        }
        return instance;
    }


    public void init(Context pContext) {
        mContext = pContext;
        clear();
    }


    public void update(Locale pLocale) {
        if (mLocale == pLocale) {
            return;
        }
        mLocale = pLocale;
        clear();
    }


    private void clear() {
        mResources.clear();
        mResources.put(0, "");
    }


    public String getString(int id) {
        String item = mResources.get(id);
        if (item == null) {
            item = mContext.getString(id);
            mResources.put(id, item);
        }
        return item;
    }

}
