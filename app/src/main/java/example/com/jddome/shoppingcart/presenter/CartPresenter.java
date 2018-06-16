package example.com.jddome.shoppingcart.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.shoppingcart.model.ShopCartModel;
import example.com.jddome.shoppingcart.view.ICartView;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class CartPresenter extends BasePresenter<ShopCartModel, ICartView> {

    public void detach() {
        this.model = null;
        this.view = null;
    }
}
