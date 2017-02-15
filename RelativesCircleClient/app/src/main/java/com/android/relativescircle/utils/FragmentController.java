package com.android.relativescircle.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class FragmentController {
    private FragmentManager _fmManager;
    private List<Fragment> _FragmentList;
    private int _containerId;

    private FragmentController(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> _listFragments, @IdRes int cid) {
        this._FragmentList = _listFragments;
        this._fmManager = fragmentManager;
        this._containerId = cid;
    }

    public static FragmentController getInstance(@NonNull FragmentManager fragmentManager, @NonNull List<Fragment> _listFragments, @IdRes int cid) {
        return new FragmentController(fragmentManager, _listFragments, cid);
    }

    public void showFragment(int fid) {
        FragmentTransaction ft = _fmManager.beginTransaction();
        hide();
        if (_FragmentList.get(fid).getActivity() == null) {
            ft.add(_containerId, _FragmentList.get(fid));
        } else {
            ft.show(_FragmentList.get(fid));
        }
        ft.commitAllowingStateLoss();
    }

    public void hide() {
        FragmentTransaction ft = _fmManager.beginTransaction();
        for (int i = 0; i < _FragmentList.size(); i++) {
            if (_FragmentList.get(i).getActivity() != null) {
                ft.hide(_FragmentList.get(i));
            }
        }
        ft.commitAllowingStateLoss();
    }
}
