package example.com.jddome.mycenter.model;

import java.io.File;

import example.com.base.mvp.BaseModel;
import example.com.jddome.mycenter.bean.GetUserInfo;
import example.com.jddome.utils.RetrofitUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author zhangjunyou
 * @date 2018/6/21
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class UploadModel extends BaseModel {

    public void getUserInfo(String uid, final IUploadModel iUploadModel) {
        RetrofitUtil.getInstence().API().getUserInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetUserInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUserInfo getUserInfo) {
                        iUploadModel.getUserInfo(getUserInfo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface IUploadModel {
        void getUserInfo(GetUserInfo getUserInfo);
    }
}
