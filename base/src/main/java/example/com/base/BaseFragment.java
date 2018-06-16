package example.com.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.base.mvp.BaseModel;
import example.com.base.mvp.BasePresenter;
import example.com.base.mvp.IBaseView;

/**
 * Created by zhangjunyou on 2018/6/7.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    public P presenter;
    private final String TAG = BaseFragment.class.getSimpleName();
    protected View rootView;
    protected Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(getLayoutid(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
        }
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attch(initModel(), this);
        }
        return rootView;
    }

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化model
     *
     * @return
     */
    protected abstract BaseModel initModel();

    /**
     * 初始化presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 获取布局ID
     *
     * @return int
     */
    protected abstract int getLayoutid();

    /**
     * 获取数据
     */
    protected abstract void initData();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach <---");
//        mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate <---");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated <---");
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart <---");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume <---");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause <---");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop <---");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView <---");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy <---");
        if (unbinder != null) {
            unbinder.unbind();//解绑
        }
        if (presenter != null) {
            presenter = null;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach <---");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void serverFail(String msg) {

    }
}
