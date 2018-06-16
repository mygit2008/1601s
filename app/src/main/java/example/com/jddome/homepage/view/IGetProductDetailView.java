package example.com.jddome.homepage.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.homepage.bean.GetProductDetail;

/**
 * @author zhangjunyou
 * @date 2018/6/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IGetProductDetailView extends IBaseView{
    void success(GetProductDetail getProductDetail);
}
