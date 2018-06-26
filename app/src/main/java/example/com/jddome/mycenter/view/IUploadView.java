package example.com.jddome.mycenter.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.mycenter.bean.GetUserInfo;

/**
 * @author zhangjunyou
 * @date 2018/6/21
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IUploadView extends IBaseView {
    void getUserInfo(GetUserInfo getUserInfo);
}
