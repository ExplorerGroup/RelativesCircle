package com.android.relativescircle.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.android.relativescircle.commom.InternetHelper;
import com.android.relativescircle.event.FinishEvent;
import com.android.relativescircle.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public abstract class BaseActivity extends FragmentActivity {
    public static int mNetState;
    public Context mApplicationContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetState = InternetHelper.getNetState(this.getApplicationContext());
        mApplicationContext = getApplicationContext();
        EventBus.getDefault().register(this);
    }

    public static int getNetState() {
        return mNetState;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netChangeEvent(NetStateChangeEvent netStateChangeEvent) {
        mNetState = InternetHelper.getNetState(this);
    }

    @Override
    protected void onStop() {
        if (isFinishing()) {
            EventBus.getDefault().unregister(this);
        }
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishAll(FinishEvent finishEvent) {
        finish();
    }
}
