package example.com.jddome.homepage.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.homepage.bean.AddCartBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import example.com.jddome.homepage.model.ProductDetailModel;
import example.com.jddome.homepage.view.IGetProductDetailView;

/**
 * @author zhangjunyou
 * @date 2018/6/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ProductDetailPresenter extends BasePresenter<ProductDetailModel, IGetProductDetailView> {

    public void getProductDetail(String pid) {
        model.getProductDetail(pid, new ProductDetailModel.IProductDetailModel() {
                    @Override
                    public void success(GetProductDetail getProductDetail) {
                        if (view != null) {
                            view.success(getProductDetail);
                        }
                    }

                    @Override
                    public void addSuccess(AddCartBean addCartBean) {

                    }
                }
        );
    }

    public void addCart(String uid, int pid) {
        model.addCart(uid, pid, new ProductDetailModel.IProductDetailModel() {
            @Override
            public void success(GetProductDetail getProductDetail) {

            }

            @Override
            public void addSuccess(AddCartBean addCartBean) {
                view.addSuccess(addCartBean);
            }
        });
    }

    public void detach() {
        if (view != null) {
            view = null;
        }
    }
}
