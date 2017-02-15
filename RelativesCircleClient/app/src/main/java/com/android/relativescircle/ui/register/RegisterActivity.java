package com.android.relativescircle.ui.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.relativescircle.R;
import com.android.relativescircle.utils.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisterFragment registerFragment =RegisterFragment.newInstance();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),registerFragment,R.id.fm_register);
        new RegisterPresenter(registerFragment);
    }
}
