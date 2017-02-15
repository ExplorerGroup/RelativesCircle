package com.android.relativescircle.ui.register;

import com.android.relativescircle.base.BasePresenter;
import com.android.relativescircle.base.BaseView;

/**
 * Created by mn on 2017/2/5 0005.
 */
public class RegisterContract {
    interface RegisterView extends BaseView<RegisterBasePresenter> {

        void encryptUserInfo();

        void initWidget();

        String register();

        String getPhoneNumber();

        String getPassWord();
    }

    interface RegisterBasePresenter extends BasePresenter {

        void register();

        boolean checkParameters();

        void initAll();
    }
}
