package example.com.jddome.shoppingcart.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.utils.RetrofitUtil;
import example.com.jddome.shoppingcart.bean.DeleteCartBean;
import example.com.jddome.shoppingcart.bean.Selectshop;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/16
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ShopCartModel extends BaseModel {

    public void selectCart(String uid, final IShopCartModel iShopCartModel) {
        RetrofitUtil.getInstence().API().selectCart(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Selectshop>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Selectshop selectshop) {
                        iShopCartModel.success(selectshop);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteCart(String uid, int pid, final IShopCartModel iShopCartModel) {
        RetrofitUtil.getInstence().API().deleteCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteCartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteCartBean deleteCartBean) {
                        iShopCartModel.delSuccess(deleteCartBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface IShopCartModel {
        void success(Selectshop selectshop);

        void delSuccess(DeleteCartBean deleteCartBean);
    }
}
