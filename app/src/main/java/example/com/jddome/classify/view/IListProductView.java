package example.com.jddome.classify.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IListProductView extends IBaseView{
    void success(ListProductBean listProductBean);
    void seekSuccess(SeekBean seekBean);
}
