package example.com.jddome.homepage.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.netutils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class GetHomeModel extends BaseModel {
    public IGetAdModel iGetAdModel;

    public void getAd(final IGetAdModel iGetAdModel) {
        RetrofitUtil.getInstence().API().getHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetAdHomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetAdHomeBean getAdBean) {
                        iGetAdModel.onSuccess(getAdBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IGetAdModel {
        void onSuccess(GetAdHomeBean getAdBean);
    }
}
