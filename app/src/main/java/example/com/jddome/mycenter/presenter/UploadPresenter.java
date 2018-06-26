package example.com.jddome.mycenter.presenter;

import java.io.File;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.mycenter.bean.GetUserInfo;
import example.com.jddome.mycenter.model.UploadModel;
import example.com.jddome.mycenter.view.IUploadView;

/**
 * @author zhangjunyou
 * @date 2018/6/21
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UploadPresenter extends BasePresenter<UploadModel, IUploadView> {

    public void getUserInfo(String uid) {
        model.getUserInfo(uid, new UploadModel.IUploadModel() {
            @Override
            public void getUserInfo(GetUserInfo getUserInfo) {
                if (view != null) {
                    view.getUserInfo(getUserInfo);
                }
            }
        });
    }
}
