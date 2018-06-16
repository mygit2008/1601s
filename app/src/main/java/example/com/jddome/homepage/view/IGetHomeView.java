package example.com.jddome.homepage.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.homepage.bean.GetAdHomeBean;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IGetHomeView extends IBaseView {
    void success(GetAdHomeBean getAdBean);
}
