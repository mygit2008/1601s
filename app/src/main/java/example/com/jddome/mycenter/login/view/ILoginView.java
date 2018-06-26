package example.com.jddome.mycenter.login.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.mycenter.login.bean.LoginBean;

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface ILoginView extends IBaseView {
    void loginSuccess(LoginBean loginBean);
}
