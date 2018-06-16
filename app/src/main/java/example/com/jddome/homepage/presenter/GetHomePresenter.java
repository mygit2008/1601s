package example.com.jddome.homepage.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.model.GetHomeModel;
import example.com.jddome.homepage.view.IGetHomeView;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class GetHomePresenter extends BasePresenter<GetHomeModel, IGetHomeView> {

    public void getAd() {
        model.getAd(new GetHomeModel.IGetAdModel() {
            @Override
            public void onSuccess(GetAdHomeBean getAdBean) {
                if (view != null) {
                    view.success(getAdBean);
                }
            }
        });
    }

    public void detach() {
        if (view != null) {
            view = null;
        }
    }
}
