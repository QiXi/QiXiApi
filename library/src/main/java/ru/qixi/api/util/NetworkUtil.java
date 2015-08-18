package ru.qixi.api.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> */

public class NetworkUtil {

	public static boolean isConnected(Context pContext) {
		ConnectivityManager manager = (ConnectivityManager)pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean online = networkInfo != null && networkInfo.isConnected();
		return online;
	}


	public static boolean wifiIsConnected(Context pContext) {
		return isConnected(pContext, ConnectivityManager.TYPE_WIFI);
	}


	public static boolean mobileIsConnected(Context pContext) {
		return isConnected(pContext, ConnectivityManager.TYPE_MOBILE);
	}


	private static boolean isConnected(Context pContext, int type) {
		ConnectivityManager manager = (ConnectivityManager)pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo = manager.getNetworkInfo(type);
		boolean online = networkInfo != null && networkInfo.isConnected();
		return online;
	}

}
