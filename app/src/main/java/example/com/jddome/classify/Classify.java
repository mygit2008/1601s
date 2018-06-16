package example.com.jddome.classify;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import example.com.base.BaseFragment;
import example.com.base.mvp.BaseModel;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.adapter.LeftClassifyAdapter;
import example.com.jddome.classify.adapter.RightClassifyAdapter;
import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;
import example.com.jddome.classify.model.ClassifyModel;
import example.com.jddome.classify.presenter.ClassifyPresenter;
import example.com.jddome.classify.view.IClassifyView;
import example.com.jddome.custom.SearchBar;

/**
 * @author zhangjunyou
 * @date 2018/6/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class Classify extends BaseFragment<ClassifyPresenter> implements IClassifyView {
    private RecyclerView mLeftClassify;
    private RecyclerView mRightClassify;
    private SimpleDraweeView simpleDraweeView;
    private SearchBar searchBar;

    @Override
    protected void initView(View view) {
        mLeftClassify = view.findViewById(R.id.left_classify);
        mRightClassify = view.findViewById(R.id.right_classify);
        searchBar = view.findViewById(R.id.line);
        simpleDraweeView = view.findViewById(R.id.log);
        mLeftClassify.setLayoutManager(new LinearLayoutManager(MyApp.context));
        mRightClassify.setLayoutManager(new LinearLayoutManager(MyApp.context));
        searchBar.setMsgOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "消息", Toast.LENGTH_SHORT).show();
            }
        });
        searchBar.setSaoOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "扫一扫", Toast.LENGTH_SHORT).show();
            }
        });
        searchBar.setSouOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApp.context, "请输入", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected BaseModel initModel() {
        return new ClassifyModel();
    }

    @Override
    protected ClassifyPresenter initPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.classify_layout;
    }

    @Override
    protected void initData() {
        presenter.getCatagory();
    }

    @Override
    public void leftClassify(ClassifyBean classifyBean) {
        final List<ClassifyBean.DataBean> data = classifyBean.getData();
        final LeftClassifyAdapter leftClassifyAdapter = new LeftClassifyAdapter(MyApp.context, data);
        mLeftClassify.setAdapter(leftClassifyAdapter);
        //条目分割线
        mLeftClassify.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //根据左侧列表第一项的cid去请求右侧的数据
        presenter.getProductCatagory(data.get(0).getCid());
        simpleDraweeView.setImageURI(data.get(0).getIcon());
        //默认选择第一个
        leftClassifyAdapter.changeCheck(0, true);
        //条目点击事件
        leftClassifyAdapter.setOnItemClickListener(new LeftClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                leftClassifyAdapter.changeCheck(position, true);
                presenter.getProductCatagory(data.get(position).getCid());
                simpleDraweeView.setImageURI(data.get(position).getIcon());
            }
        });
    }

    @Override
    public void rightClassify(ClassifyChildBean classifyChildBean) {
        List<ClassifyChildBean.DataBean> data = classifyChildBean.getData();
        RightClassifyAdapter rightClassifyAdapter = new RightClassifyAdapter(MyApp.context, data);
        mRightClassify.setAdapter(rightClassifyAdapter);
    }
}
