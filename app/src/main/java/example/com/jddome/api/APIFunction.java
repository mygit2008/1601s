package example.com.jddome.api;

import java.io.File;
import java.util.Map;

import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;
import example.com.jddome.homepage.bean.AddCartBean;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import example.com.jddome.mycenter.bean.GetUserInfo;
import example.com.jddome.mycenter.login.bean.LoginBean;
import example.com.jddome.mycenter.register.bean.RegBean;
import example.com.jddome.shoppingcart.bean.DeleteCartBean;
import example.com.jddome.shoppingcart.bean.Selectshop;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface APIFunction {
    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @GET("user/login")
    Observable<LoginBean> login(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @return
     */
    @GET("user/reg")
    Observable<RegBean> register(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 获取用户信息
     *
     * @param uid
     * @return
     */
    @GET("user/getUserInfo")
    Observable<GetUserInfo> getUserInfo(@Query("uid") String uid);

    /**
     * 上传头像
     *
     * @param file
     * @param uid
     * @return
     */
    @Multipart
    @POST("file/upload")
    Observable<ResponseBody> upload(@Part("file") File file, @Query("uid") String uid);

    /**
     * 首页
     *
     * @return
     */
    @GET("home/getHome")
    Observable<GetAdHomeBean> getHome();

    /**
     * 商品详情
     *
     * @param pid
     * @return
     */
    @GET("product/getProductDetail")
    Observable<GetProductDetail> getProductDetail(@Query("pid") String pid);

    /**
     * 分类
     *
     * @return
     */
    @GET("product/getCatagory")
    Observable<ClassifyBean> getCatagory();

    /**
     * 子分类
     *
     * @return
     */
    @GET("product/getProductCatagory")
    Observable<ClassifyChildBean> getProductCatagory(@Query("cid") int cid);

    /**
     * 获取商品列表
     *
     * @param pscid
     * @return
     */
    @GET("product/getProducts")
    Observable<ListProductBean> getProducts(@Query("pscid") int pscid, @Query("page") int page);

    /**
     * 搜索商品
     *
     * @param keywords
     * @param page
     * @return
     */
    @GET("product/searchProducts")
    Observable<SeekBean> searchProducts(@Query("keywords") String keywords, @Query("page") int page);

    /**
     * 查询购物车
     *
     * @param uid
     * @return
     */
    @GET("product/getCarts")
    Observable<Selectshop> selectCart(@Query("uid") String uid);

    /**
     * 添加购物车
     *
     * @param uid
     * @param pid
     * @return
     */
    @GET("product/addCart")
    Observable<AddCartBean> addCart(@Query("uid") String uid, @Query("pid") int pid);

    /**
     * 删除购物车
     *
     * @param uid
     * @param pid
     * @return
     */
    @GET("product/deleteCart")
    Observable<DeleteCartBean> deleteCart(@Query("uid") String uid, @Query("pid") int pid);
}
