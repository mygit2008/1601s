package example.com.jddome.classify.view;

import example.com.base.mvp.IBaseView;
import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface IClassifyView extends IBaseView {
    void leftClassify(ClassifyBean classifyBean);
    void rightClassify(ClassifyChildBean classifyChildBean);
}
