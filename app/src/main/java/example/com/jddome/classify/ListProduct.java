package example.com.jddome.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.base.BaseActivity;
import example.com.base.mvp.BaseModel;
import example.com.base.mvp.IBaseView;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.adapter.ListProductAdapter;
import example.com.jddome.classify.adapter.Seekdapter;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.classify.bean.SeekBean;
import example.com.jddome.classify.model.ListProductModel;
import example.com.jddome.classify.presenter.ListProductPresenter;
import example.com.jddome.classify.view.IListProductView;
import example.com.jddome.homepage.view.ProductDetail;

public class ListProduct extends BaseActivity<ListProductPresenter> implements IListProductView, IBaseView {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_search)
    ImageView tv_search;
    @BindView(R.id.xrlv)
    XRecyclerView mXrlv;
    private int pscid;
    private int page = 1;
    private int id;
    private String pname;

    @Override
    protected BaseModel initModel() {
        return new ListProductModel();
    }

    @Override
    protected ListProductPresenter initPresenter() {
        return new ListProductPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_list_product;
    }

    @Override
    protected void initData() {
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 1);
        pname = intent.getStringExtra("pname");
        id = intent.getIntExtra("id", 1);
        mXrlv.setLayoutManager(new LinearLayoutManager(MyApp.context));

        if (id == 2) {
            presenter.getSeekProduct(pname, page);
        } else {
            presenter.getProducts(pscid, page);
        }
    }

    @Override
    public void success(ListProductBean listProductBean) {
        List<ListProductBean.DataBean> data = listProductBean.getData();
        ListProductAdapter adapter = new ListProductAdapter(MyApp.context, data);
        mXrlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, ListProductBean.DataBean dataBean) {
                Intent intent = new Intent(MyApp.context, ProductDetail.class);
                intent.putExtra("id", 1);
                intent.putExtra("pid", dataBean.getPid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void seekSuccess(SeekBean seekBean) {
        List<SeekBean.DataBean> data = seekBean.getData();
        Seekdapter seekdapter = new Seekdapter(MyApp.context, data);
        mXrlv.setAdapter(seekdapter);
        et_search.setText(pname + "");
    }


    @OnClick({R.id.back, R.id.et_search, R.id.tv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.tv_search:
                break;
        }
    }
}
