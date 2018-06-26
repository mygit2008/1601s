package example.com.jddome.mycenter.register.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.mycenter.register.bean.RegBean;

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IRegister extends IBaseView{
    void success(RegBean regBean);
}
