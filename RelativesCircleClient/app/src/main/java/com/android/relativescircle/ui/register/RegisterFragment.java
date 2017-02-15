package com.android.relativescircle.ui.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.relativescircle.R;
import com.android.relativescircle.base.BaseFragment;
import com.android.relativescircle.commom.FtServer;
import com.android.relativescircle.dto.UserDetail;
import com.android.relativescircle.utils.EncryptUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mn on 2017/2/5 0001.
 */

public final class RegisterFragment extends BaseFragment implements RegisterContract.RegisterView {

    @BindView(R.id.tiet_phone_number)
    TextInputEditText tiet_phone_number;
    @BindView(R.id.tiet_password)
    TextInputEditText tiet_password;
    @BindView(R.id.tiet_vercode)
    TextInputEditText tiet_vercode;
    @BindView(R.id.btn_register)
    AppCompatButton btn_register;

    View mParentView;
    RegisterContract.RegisterBasePresenter mPresenter;
    private String mPassword;
    private String userName;
    private String encryptName;
    private String encryptPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, mParentView);
        mPresenter.initAll();
        return mParentView;
    }


    @OnClick(R.id.btn_register)
    public void onclick(){
        boolean isValidate = mPresenter.checkParameters();
        if (isValidate) {
            mPresenter.register();
        }
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void encryptUserInfo() {
        byte[] realkeyUsername = EncryptUtils.encryptHashCode((userName + mPassword).hashCode());
        byte[] realkeyPassword = EncryptUtils.encryptHashCode((mPassword + userName).hashCode());
        try {
            encryptName = EncryptUtils.aesEncrypt(userName, realkeyUsername);
            encryptPassword = EncryptUtils.aesEncrypt(mPassword, realkeyPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWidget() {
        tiet_phone_number.setError("手机号不能为空");
        tiet_password.setError("密码不能为空");
        tiet_vercode.setError("验证码不能为空");
    }

    @Override
    public String register() {
        return FtServer.getPPChatServerInstance(mApplicationContext).register(encryptName, encryptPassword, EncryptUtils.md5_16bytes(userName), UserDetail.USER_TYPE_DEFAULT);
    }

    @Override
    public String getPhoneNumber() {
        userName = tiet_phone_number.getText().toString();
        return userName;
    }

    @Override
    public String getPassWord() {
        mPassword = tiet_password.getText().toString();
        return mPassword;
    }

    @Override
    public void setPresenter(RegisterContract.RegisterBasePresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showAction(String actionString) {
        Snackbar.make(mParentView, actionString, Snackbar.LENGTH_LONG)
                .setAction("提示", null).show();
    }

    @Override
    public void onDestroy() {
        mPresenter.unSubscribe();
        super.onDestroy();
    }
}
