package com.android.relativescircle.ui.login;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.relativescircle.R;
import com.android.relativescircle.base.BaseActivity;

import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginFragment loginFragment = LoginFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fm_login,loginFragment);
        fragmentTransaction.commitAllowingStateLoss();
        new LoginPresenter(loginFragment);
    }


}
