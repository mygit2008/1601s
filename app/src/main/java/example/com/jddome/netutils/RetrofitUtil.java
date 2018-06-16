package example.com.jddome.netutils;

import java.util.concurrent.TimeUnit;

import example.com.jddome.api.APIFunction;
import example.com.jddome.api.BaseAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil;
    private static APIFunction apiFunction;

    private RetrofitUtil() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                //添加日志拦截器
//                .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseAPI.GETAD)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        apiFunction = mRetrofit.create(APIFunction.class);
    }

    public static RetrofitUtil getInstence() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null)
                    retrofitUtil = new RetrofitUtil();
            }

        }
        return retrofitUtil;
    }


    public APIFunction API() {
        return apiFunction;
    }
}
