package com.android.relativescircle.ui.login;

import com.android.relativescircle.base.BasePresenter;
import com.android.relativescircle.base.BaseView;

/**
 * Created by mn on 2016/11/30 0030.
 * This specifies the contract between the view and the presenter.
 */

public interface LoginContract {

    interface LoginView extends BaseView<LoginPresenter> {

        void startMainActivity();

        void startRegisterActivity();

        void encryptUserInfo();

        void initWidget();

        String login();

        String getPhoneNumber();

        String getPassWord();
    }

    interface LoginPresenter extends BasePresenter {

        void login();

        boolean checkParameters();

        void initAll();
    }
}
