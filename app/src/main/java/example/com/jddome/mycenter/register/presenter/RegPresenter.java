package example.com.jddome.mycenter.register.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.MyApp;
import example.com.jddome.mycenter.register.bean.RegBean;
import example.com.jddome.mycenter.register.model.RegModel;
import example.com.jddome.mycenter.register.view.IRegister;
import example.com.jddome.utils.Aerifly;

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RegPresenter extends BasePresenter<RegModel, IRegister> {

    public void register(String mobile, String pwd) {
        if (Aerifly.isMobile(MyApp.context, mobile) && Aerifly.isPassword(MyApp.context, pwd))
            model.register(mobile, pwd, new RegModel.IRegModel() {
                @Override
                public void success(RegBean regBean) {
                    if (view != null) {
                        view.success(regBean);
                    }
                }
            });
    }
}
