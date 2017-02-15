package com.android.relativescircle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.relativescircle.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;


public class NetBroadcastReceiver extends BroadcastReceiver {
	private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(NET_CHANGE_ACTION)) {
			EventBus.getDefault().post(new NetStateChangeEvent());
		}
	}
}
