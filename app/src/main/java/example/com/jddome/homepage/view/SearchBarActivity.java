package example.com.jddome.homepage.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.ListProduct;
import example.com.jddome.custom.FlowLayout;
import example.com.jddome.greendao.GoodsDao;
import example.com.jddome.homepage.adapter.MyAdapter;
import example.com.jddome.homepage.bean.Goods;

public class SearchBarActivity extends AppCompatActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.flow_hot_search)
    FlowLayout flowHotSearch;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.lv_search)
    RecyclerView lvSearch;
    private String[] rs = {"手机", "笔记本", "坚果", "月饼", "欧式壁灯", "吸尘器干湿两用", "户外登山鞋", "无线恐引起", "洗面奶", "泡茶壶"};
    private GoodsDao goodsDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        ButterKnife.bind(this);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lvSearch.setLayoutManager(linearLayoutManager);
        goodsDao = MyApp.daoSession.getGoodsDao();
        initChildViews();
    }

    @OnClick({R.id.et_search, R.id.tv_search, R.id.iv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_search:
                String s = etSearch.getText().toString();
                Intent it = new Intent(SearchBarActivity.this, ListProduct.class);
                it.putExtra("id", 2);
                it.putExtra("pname", s);
                startActivity(it);
                break;
            case R.id.iv_delete:
                goodsDao.deleteAll();
                history();
                break;
        }
    }

    private void initChildViews() {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < rs.length; i++) {
            final Button view = new Button(this);
            view.setText(rs[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tv_shape));
            flowHotSearch.addView(view, lp);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etSearch.setText(view.getText().toString());
                    String s = etSearch.getText().toString();
                    Goods goods = new Goods();
                    goods.setGname(s);
                    goodsDao.insert(goods);
                    history();
                    Intent it = new Intent(SearchBarActivity.this, ListProduct.class);
                    it.putExtra("id", 2);
                    it.putExtra("pname", s);
                    startActivity(it);
                }
            });
        }
    }

    public void history() {
        List<Goods> goodses = goodsDao.queryBuilder().list();
        Log.e("select2------>", goodses.size() + "");
        MyAdapter adapter = new MyAdapter(goodses, MyApp.context);
        lvSearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
