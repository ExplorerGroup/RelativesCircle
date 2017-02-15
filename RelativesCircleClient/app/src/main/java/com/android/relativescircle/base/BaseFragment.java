package com.android.relativescircle.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public abstract class BaseFragment extends Fragment{
    protected Context mApplicationContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mApplicationContext = getActivity().getApplicationContext();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
