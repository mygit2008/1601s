package example.com.jddome.classify.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;
import example.com.jddome.netutils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ClassifyModel extends BaseModel {
    private IClassifyModel iClassifyModel;

    public void getCatagory(final IClassifyModel iClassifyModel) {
        RetrofitUtil.getInstence().API().getCatagory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifyBean classifyBean) {
                        iClassifyModel.leftClassify(classifyBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getProductCatagory(int cid, final IClassifyModel iClassifyModel) {
        RetrofitUtil.getInstence().API().getProductCatagory(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassifyChildBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ClassifyChildBean classifyChildBean) {
                        iClassifyModel.rightClassify(classifyChildBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IClassifyModel {
        void leftClassify(ClassifyBean classifyBean);

        void rightClassify(ClassifyChildBean classifyChildBean);
    }
}
