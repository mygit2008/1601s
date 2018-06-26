package example.com.jddome.homepage.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.homepage.bean.AddCartBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import example.com.jddome.homepage.view.IGetProductDetailView;
import example.com.jddome.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/10
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ProductDetailModel extends BaseModel {
    private IGetProductDetailView iGetProductDetailView;

    public void getProductDetail(String pid, final IProductDetailModel iProductDetailModel) {
        RetrofitUtil.getInstence().API().getProductDetail(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetProductDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetProductDetail getProductDetail) {
                        iProductDetailModel.success(getProductDetail);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addCart(String uid, int pid, final IProductDetailModel iProductDetailModel) {
        RetrofitUtil.getInstence().API().addCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        iProductDetailModel.addSuccess(addCartBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IProductDetailModel {
        void success(GetProductDetail getProductDetail);

        void addSuccess(AddCartBean addCartBean);
    }
}
