package example.com.jddome.classify.presenter;

import example.com.base.mvp.BasePresenter;
import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;
import example.com.jddome.classify.model.ClassifyModel;
import example.com.jddome.classify.view.IClassifyView;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ClassifyPresenter extends BasePresenter<ClassifyModel, IClassifyView> {
    public void getCatagory() {
        model.getCatagory(new ClassifyModel.IClassifyModel() {
            @Override
            public void leftClassify(ClassifyBean classifyBean) {
                view.leftClassify(classifyBean);
            }

            @Override
            public void rightClassify(ClassifyChildBean classifyChildBean) {

            }
        });
    }

    public void getProductCatagory(int cid) {
        model.getProductCatagory(cid, new ClassifyModel.IClassifyModel() {
            @Override
            public void leftClassify(ClassifyBean classifyBean) {

            }

            @Override
            public void rightClassify(ClassifyChildBean classifyChildBean) {
                view.rightClassify(classifyChildBean);
            }
        });
    }

    public void detach() {
        if (view != null) {
            view = null;
        }
    }
}
