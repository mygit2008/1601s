package example.com.jddome.api;

import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public interface APIFunction {
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
}
