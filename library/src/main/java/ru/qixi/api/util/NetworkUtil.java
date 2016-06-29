package ru.qixi.api.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/* <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> */


public class NetworkUtil {

    public static final int CONNECTION_UNKNOWN  = -1;
    public static final int CONNECTION_NONE     = 0;
    public static final int CONNECTION_MOBILE   = 1;
    public static final int CONNECTION_WIFI     = 2;
    public static final int CONNECTION_ETHERNET = 3;


    public static boolean isConnected(int connectionType) {
        return (connectionType == CONNECTION_NONE) ? false : true;
    }


    public static String getNetworkTypeName(int type) {
        switch (type) {
            case CONNECTION_NONE:
                return "NONE";
            case CONNECTION_WIFI:
                return "WIFI";
            case CONNECTION_MOBILE:
                return "MOBILE";
            case CONNECTION_ETHERNET:
                return "ETHERNET";
            case CONNECTION_UNKNOWN:
                return "UNKNOWN";
            default:
                return Integer.toString(type);
        }
    }


    public static int getCurrentConnectionType(Context pContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return getCurrentConnectionType(connectivityManager);
    }


    public static int getCurrentConnectionType(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return CONNECTION_NONE;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()) {
            return CONNECTION_NONE;
        }

        switch (networkInfo.getType()) {
            case ConnectivityManager.TYPE_WIFI:
                return CONNECTION_WIFI;
            case ConnectivityManager.TYPE_ETHERNET:
                return CONNECTION_ETHERNET;
            case ConnectivityManager.TYPE_WIMAX:
                return CONNECTION_MOBILE;
            case ConnectivityManager.TYPE_MOBILE:
                return CONNECTION_MOBILE;
            default:
                return CONNECTION_UNKNOWN;
        }
    }


    public static boolean isConnectingToInternet(Context pContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return isConnectingToInternet(connectivityManager);
    }


    public static boolean isConnectingToInternet(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network network : networks) {
                networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } else {
            NetworkInfo[] networksInfo = connectivityManager.getAllNetworkInfo();
            if (networksInfo != null) {
                for (NetworkInfo info : networksInfo) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        Log.dt("Network", "name: " + info.getTypeName());
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
