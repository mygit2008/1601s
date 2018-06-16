package example.com.jddome.shoppingcart.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.shoppingcart.bean.CartBean;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ShopCartModel extends BaseModel {

    public interface IShopCartModel {
        void success(CartBean cartBean);
    }
}
