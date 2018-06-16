package example.com.base.mvp;

/**
 * @author zhangjunyou
 * @date 2018/6/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class BasePresenter<M extends BaseModel, V extends IBaseView> implements IBasePresenter {
    public M model;
    public V view;

    /**
     * 绑定M和V
     *
     * @param m
     * @param v
     */
    public void attch(M m, V v) {
        this.model = m;
        this.view = v;
    }

    /**
     * 回收资源，解绑，避免内存泄漏
     */
    public void detach() {
        this.model = null;
        this.view = null;
    }
}
