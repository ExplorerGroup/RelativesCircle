package com.android.relativescircle.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.android.relativescircle.ui.main.MainActivity;
import com.android.relativescircle.ui.register.RegisterActivity;
import com.android.relativescircle.utils.AppUtils;
import com.android.relativescircle.utils.EncryptUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends BaseFragment implements LoginContract.LoginView {
    @BindView(R.id.tiet_phone_number)
    TextInputEditText tiet_phone_number;
    @BindView(R.id.tiet_password)
    TextInputEditText tiet_password;
    LoginContract.LoginPresenter mPresenter;
    @BindView(R.id.btn_login)
    AppCompatButton btn_login;

    View mParentView;

    private String phoneNumber;
    private String password;
    private String encryptName;
    private String encryptPassword;

    public LoginFragment() {
    }


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, mParentView);
        mPresenter.initAll();
        return mParentView;
    }

    @OnClick({R.id.btn_login,R.id.tv_regist,R.id.tv_forget_pwd})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                boolean isValidate = mPresenter.checkParameters();
                if (isValidate) {
                    mPresenter.login();
                }
                break;
            case R.id.tv_regist:
                startRegisterActivity();
                break;
            case R.id.tv_forget_pwd:
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void startMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void encryptUserInfo() {
        byte[] realkeyUsername = EncryptUtils.encryptHashCode((phoneNumber + password).hashCode());
        byte[] realkeyPassword = EncryptUtils.encryptHashCode((password + phoneNumber).hashCode());
        try {
            encryptName = EncryptUtils.aesEncrypt(phoneNumber, realkeyUsername);
            encryptPassword = EncryptUtils.aesEncrypt(password, realkeyPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWidget() {
        tiet_password.setError("密码不能为空");
        tiet_phone_number.setError("手机号码不能为空");
    }


    @Override
    public String login() {
        return FtServer.getPPChatServerInstance(mApplicationContext).login(encryptName, encryptPassword,
                "Android" + Build.VERSION.SDK_INT, AppUtils.getUniqueID(), EncryptUtils.md5_16bytes(phoneNumber), "上海市", UserDetail.USER_TYPE_DEFAULT);
    }

    @Override
    public String getPhoneNumber() {
        phoneNumber = tiet_phone_number.getText().toString();
        return tiet_phone_number.getText().toString();
    }

    @Override
    public String getPassWord() {
        password = tiet_password.getText().toString();
        return password;
    }

    @Override
    public void showAction(String hint) {
        Snackbar.make(mParentView, hint, Snackbar.LENGTH_LONG)
                .setAction("提示", null).show();
    }

    @Override
    public void onDestroy() {
        mPresenter.unSubscribe();
        super.onDestroy();
    }
}
