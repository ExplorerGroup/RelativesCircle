package com.android.relativescircle.ui.login;

import android.support.annotation.NonNull;

import com.android.relativescircle.base.BaseActivity;
import com.android.relativescircle.commom.InternetHelper;
import com.android.relativescircle.commom.RetCode;
import com.android.relativescircle.commom.RetTemplate;
import com.android.relativescircle.utils.JackSonUtil;
import com.android.relativescircle.utils.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView mloginView;
    private CompositeSubscription mSubscriptions;

    public LoginPresenter(@NonNull LoginContract.LoginView loginView) {
        mloginView = checkNotNull(loginView, "login view can't null");
        loginView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void login() {
        mloginView.encryptUserInfo();
        int netState = BaseActivity.getNetState();
        if (netState == InternetHelper.I_NET_NONE) {
            mloginView.showAction("网络异常");
            return;
        }
        Subscription subscription = rx.Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return mloginView.login();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        RetTemplate retTemplate = JackSonUtil.json2Obj(s, RetTemplate.class);
                        if (retTemplate == null) {
                            mloginView.showAction("未知错误");
                        } else {
                            switch (retTemplate.getRetCode()) {
                                case RetCode.RETCODE_SUCCESS:
                                    mloginView.startMainActivity();
                                    break;
                                case RetCode.RETCODE_PASSWOER_ERR:
                                    mloginView.showAction("密码错误");
                                    break;
                                case RetCode.RETCODE_PATAM_TYPE_ERR:
                                    mloginView.showAction("参数错误");
                                    break;
                                case RetCode.RETCODE_USER_NON_EXIST:
                                    mloginView.showAction("用户不存在");
                                    break;
                            }
                        }

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public boolean checkParameters() {
        String phoneNumber = mloginView.getPhoneNumber();
        String password = mloginView.getPassWord();
        if (!StringUtils.isMobileNO(phoneNumber)) {
            mloginView.showAction("请输入正确的手机号码");
            return false;
        }
        if (password.length() < 6 || password.length() > 16) {
            mloginView.showAction("密码长度为6-16位之间");
            return false;
        }
        return true;
    }

    @Override
    public void initAll() {
        mloginView.initWidget();
    }
}
