package example.com.jddome.homepage;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import example.com.base.BaseFragment;
import example.com.base.mvp.BaseModel;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.custom.MyScrollView;
import example.com.jddome.custom.SearchBar;
import example.com.jddome.homepage.adapter.GetHomeAdapter;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.model.GetHomeModel;
import example.com.jddome.homepage.presenter.GetHomePresenter;
import example.com.jddome.homepage.view.IGetHomeView;
import example.com.jddome.homepage.view.SearchBarActivity;

/**
 * @author zhangjunyou
 * @date 2018/6/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class HomePage extends BaseFragment<GetHomePresenter> implements IGetHomeView {
    private View view;
    private RecyclerView mRlv;
    private SearchBar line;
    private MyScrollView scrollView;
    private int imageHeight = 50; //设置渐变高度，一般为导航图片高度，自己控制

    @Override
    protected void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        line = (SearchBar) view.findViewById(R.id.line);
        scrollView = (MyScrollView) view.findViewById(R.id.scrollView);
        //搜索框在布局最上面
        line.bringToFront();
        //滑动监听
        scrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    line.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    line.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });
        line.setMsgOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "消息", Toast.LENGTH_SHORT).show();
            }
        });
        line.setSaoOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "扫一扫", Toast.LENGTH_SHORT).show();
            }
        });
        line.setSouOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "请输入", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApp.context, SearchBarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected BaseModel initModel() {
        return new GetHomeModel();
    }

    @Override
    protected GetHomePresenter initPresenter() {
        return new GetHomePresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.homepage_layout;
    }

    @Override
    protected void initData() {
        presenter.getAd();
    }

    @Override
    public void success(GetAdHomeBean getAdHomeBean) {
        GetAdHomeBean.DataBean data = getAdHomeBean.getData();
        mRlv.setLayoutManager(new LinearLayoutManager(MyApp.context));
        GetHomeAdapter adAdapter = new GetHomeAdapter(MyApp.context, data);
        mRlv.setAdapter(adAdapter);
    }
}
