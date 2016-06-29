package ru.qixi.api.util;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


/**
 * Created by qixi on 14.01.16.
 */
public class FileUtil {


    /**
     * Reads the text of an asset. Should not be run on the UI thread.
     *
     * @param mgr      The {@link AssetManager} obtained via {@link Context#getAssets()}
     * @param fileName The path to the asset.
     * @return The plain text of the asset
     */
    public static void readAsset(List<String> list, AssetManager mgr, String fileName) {
        list.clear();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = mgr.open(fileName);
            reader = new BufferedReader(new InputStreamReader(is));
            String text;
            while ((text = reader.readLine()) != null) {
                list.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    public static void readAsset(Map<String, String> map, AssetManager mgr, String fileName) {
        //map.clear();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = mgr.open(fileName);
            reader = new BufferedReader(new InputStreamReader(is));
            String text;
            while ((text = reader.readLine()) != null) {
                map.put(text, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

}
