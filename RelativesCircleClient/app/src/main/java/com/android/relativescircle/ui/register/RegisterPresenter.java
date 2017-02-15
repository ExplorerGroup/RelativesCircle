package com.android.relativescircle.ui.register;

import com.android.relativescircle.base.BaseActivity;
import com.android.relativescircle.commom.InternetHelper;
import com.android.relativescircle.commom.RetCode;
import com.android.relativescircle.commom.RetTemplate;
import com.android.relativescircle.utils.JackSonUtil;
import com.android.relativescircle.utils.StringUtils;

import rx.Observable;
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

public final class RegisterPresenter implements RegisterContract.RegisterBasePresenter {
    private RegisterContract.RegisterView mRegisterView;
    private CompositeSubscription mSubscriptions;

    @Override
    public void subscribe() {
    }

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        mRegisterView = checkNotNull(registerView, "register view Can't null");
        registerView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }


    @Override
    public void register() {
        mRegisterView.encryptUserInfo();
        int netState = BaseActivity.getNetState();
        if (netState == InternetHelper.I_NET_NONE) {
            mRegisterView.showAction("网络异常");
            return;
        }
        Subscription subscription = Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return mRegisterView.register();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        RetTemplate retTemplate = JackSonUtil.json2Obj(s, RetTemplate.class);
                        if (retTemplate == null) {
                            mRegisterView.showAction("注册失败");
                        } else {
                            switch (retTemplate.getRetCode()) {
                                case RetCode.RETCODE_SUCCESS:
                                    mRegisterView.showAction("注册成功");
                                    break;
                                case RetCode.RETCODE_USER_EXIST:
                                    mRegisterView.showAction("用户名已存在");
                                    break;
                                case RetCode.RECODED_PARAM_PARSE_ERR:
                            }
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public boolean checkParameters() {
        String phoneNumber = mRegisterView.getPhoneNumber();
        String password = mRegisterView.getPassWord();
        if (!StringUtils.isMobileNO(phoneNumber)) {
            mRegisterView.showAction("请输入正确的手机号码");
            return false;
        }
        if (password.length() < 6 || password.length() > 16) {
            mRegisterView.showAction("密码长度为6-16位之间");
            return false;
        }
        return true;
    }

    @Override
    public void initAll() {
        mRegisterView.initWidget();
    }
}
