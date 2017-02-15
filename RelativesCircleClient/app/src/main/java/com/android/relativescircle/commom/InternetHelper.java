package com.android.relativescircle.commom;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络判断
 */
public class InternetHelper {
	public static final int I_NET_NONE = 0;
	public static final int I_NET_WIFI = 1;
	public static final int I_NET_MOBILE = 2;
	public static final int I_NET_ETHER = 3;

	/**
	 * 检测网络是否可用
	 *
	 * @return
	 */
	public static int getNetState(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo wifiNet = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNet != null && wifiNet.isConnected()) {
			return I_NET_WIFI;
		}
		NetworkInfo mobileNet = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNet != null && mobileNet.isConnected()) {
			return I_NET_MOBILE;
		}
		NetworkInfo ethernet = conn.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
		if (ethernet != null && ethernet.isConnected()) {
			return I_NET_ETHER;
		}
		return I_NET_NONE;
	}
}
