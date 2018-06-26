package example.com.jddome.shoppingcart.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.shoppingcart.bean.DeleteCartBean;
import example.com.jddome.shoppingcart.bean.Selectshop;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface ICartView extends IBaseView{
    void success(Selectshop selectshop);
    void delSuccess(DeleteCartBean deleteCartBean);
}
