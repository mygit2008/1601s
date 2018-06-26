package example.com.jddome.utils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import example.com.jddome.api.APIFunction;
import example.com.jddome.api.BaseAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    /**
     * post请求上传文件
     * 参数1 url
     * 参数2 回调Callback
     */
    public static void upLoadFile(String path, Map<String, Object> params, Callback callback) {

        //创建OkHttpClient请求对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS)
                .writeTimeout(5000, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        //设设置类型 以表单的形式提交
        builder.setType(MultipartBody.FORM);

        for (Map.Entry<String, Object> entry : params.entrySet()) {

            Object object = entry.getValue();
            if (!(object instanceof File)) {
                builder.addFormDataPart(entry.getKey(), object.toString());
            } else {
                File file = (File) object;
                builder.addFormDataPart(entry.getKey(), file.getName().trim(),
                        RequestBody.create(MediaType.parse("img/png"), file));

                //img/png -> application/octet-stream
            }
        }

        Request request = new Request.Builder()
                .post(builder.build())
                .url(path)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
