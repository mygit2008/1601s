package example.com.jddome.shoppingcart.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.shoppingcart.bean.DeleteCartBean;
import example.com.jddome.shoppingcart.bean.Selectshop;
import example.com.jddome.shoppingcart.model.ShopCartModel;
import example.com.jddome.shoppingcart.view.ICartView;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class CartPresenter extends BasePresenter<ShopCartModel, ICartView> {

    public void selectCart(String uid) {
        model.selectCart(uid, new ShopCartModel.IShopCartModel() {
            @Override
            public void success(Selectshop selectshop) {
                if (view != null) {
                    view.success(selectshop);
                }
            }

            @Override
            public void delSuccess(DeleteCartBean deleteCartBean) {

            }
        });
    }

    public void deleteCart(String uid, int pid) {
        model.deleteCart(uid, pid, new ShopCartModel.IShopCartModel() {
            @Override
            public void success(Selectshop selectshop) {

            }

            @Override
            public void delSuccess(DeleteCartBean deleteCartBean) {
                if (view != null) {
                    view.delSuccess(deleteCartBean);
                }
            }
        });
    }

    public void detach() {
        this.model = null;
        this.view = null;
    }
}
