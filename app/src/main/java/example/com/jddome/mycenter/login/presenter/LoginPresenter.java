package example.com.jddome.mycenter.login.presenter;


import android.widget.EditText;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.MyApp;
import example.com.jddome.mycenter.login.bean.LoginBean;
import example.com.jddome.mycenter.login.model.LoginModel;
import example.com.jddome.mycenter.login.view.ILoginView;
import example.com.jddome.utils.Aerifly;

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class LoginPresenter extends BasePresenter<LoginModel, ILoginView> {
    public void login(String mobile, String pwd) {
        if (Aerifly.isMobile(MyApp.context, mobile) && Aerifly.isPassword(MyApp.context, pwd)) {
            model.login(mobile, pwd, new LoginModel.ILoginModel() {
                @Override
                public void loginSuccess(LoginBean loginBean) {
                    view.loginSuccess(loginBean);
                }
            });
        }
    }
}
