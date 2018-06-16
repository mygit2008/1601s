package example.com.jddome.classify.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;
import example.com.jddome.netutils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ListProductModel extends BaseModel {

    public void getProducts(int pscid, int page, final IListProductModel iListProductModel) {
        RetrofitUtil.getInstence().API().getProducts(pscid, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListProductBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListProductBean listProductBean) {
                        if (iListProductModel != null) {
                            iListProductModel.success(listProductBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSeekProduct(String pname, int page, final IListProductModel iListProductModel) {
        RetrofitUtil.getInstence().API().searchProducts(pname, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SeekBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SeekBean seekBean) {
                        iListProductModel.success(seekBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IListProductModel {
        void success(ListProductBean listProductBean);

        void success(SeekBean seekBean);
    }
}
