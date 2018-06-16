package example.com.jddome.shoppingcart.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.shoppingcart.bean.CartBean;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface ICartView extends IBaseView{
    void success(CartBean cartBean);
}
