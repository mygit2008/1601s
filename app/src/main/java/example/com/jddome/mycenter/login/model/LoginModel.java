package example.com.jddome.mycenter.login.model;

import example.com.base.mvp.BaseModel;
import example.com.jddome.mycenter.login.bean.LoginBean;
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

public class LoginModel extends BaseModel {

    public void login(String mobile, String pwd, final ILoginModel iLoginModel) {
        RetrofitUtil.getInstence().API().login(mobile, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iLoginModel.loginSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface ILoginModel {
        void loginSuccess(LoginBean loginBean);
    }
}
