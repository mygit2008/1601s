package example.com.jddome.classify.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;
import example.com.jddome.classify.model.ListProductModel;
import example.com.jddome.classify.view.IListProductView;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ListProductPresenter extends BasePresenter<ListProductModel, IListProductView> {

    public void getProducts(int pscid, int page) {
        model.getProducts(pscid, page, new ListProductModel.IListProductModel() {
            @Override
            public void success(ListProductBean listProductBean) {
                if (view != null) {
                    view.success(listProductBean);
                }
            }

            @Override
            public void success(SeekBean seekBean) {

            }
        });
    }

    public void getSeekProduct(String pname, int page) {
        model.getSeekProduct(pname, page, new ListProductModel.IListProductModel() {
            @Override
            public void success(ListProductBean listProductBean) {

            }

            @Override
            public void success(SeekBean seekBean) {
                if (view != null) {
                    view.seekSuccess(seekBean);
                }
            }
        });
    }


    public void detach() {
        this.model = null;
        this.view = null;
    }
}
