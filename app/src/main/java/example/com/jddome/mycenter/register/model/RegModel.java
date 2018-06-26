package example.com.jddome.mycenter.register.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.mycenter.register.bean.RegBean;
import example.com.jddome.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangjunyou
 * @date 2018/6/20
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RegModel extends BaseModel {

    public void register(String mobile, String pwd, final IRegModel iRegModel) {
        RetrofitUtil.getInstence().API().register(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        iRegModel.success(regBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IRegModel {
        void success(RegBean regBean);
    }
}
