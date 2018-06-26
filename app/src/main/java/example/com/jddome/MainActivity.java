package example.com.jddome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import example.com.jddome.classify.Classify;
import example.com.jddome.fragment.Find;
import example.com.jddome.homepage.HomePage;
import example.com.jddome.mycenter.Mine;
import example.com.jddome.shoppingcart.ShoppingCart;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout mContainer;
    private RadioButton mHome;
    private RadioButton mCla;
    private RadioButton mCar;
    private RadioButton mFind;
    private RadioButton mMy;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        int id = getIntent().getIntExtra("id", 0);//获取intent值
        if (id == 2) {//判断intent值
            mCar.performClick();//给按钮设置状态
        } else {
            mHome.performClick();
        }
    }

    private void initView() {
        mContainer = (FrameLayout) findViewById(R.id.container);
        mHome = (RadioButton) findViewById(R.id.home);
        mHome.setOnClickListener(this);
        mCla = (RadioButton) findViewById(R.id.cla);
        mCla.setOnClickListener(this);
        mCar = (RadioButton) findViewById(R.id.car);
        mCar.setOnClickListener(this);
        mFind = (RadioButton) findViewById(R.id.find);
        mFind.setOnClickListener(this);
        mMy = (RadioButton) findViewById(R.id.my);
        mMy.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                break;
            case R.id.home:
                fragmentManager.beginTransaction().replace(R.id.container, new HomePage()).commit();
                break;
            case R.id.cla:
                fragmentManager.beginTransaction().replace(R.id.container, new Classify()).commit();
                break;
            case R.id.car:
                fragmentManager.beginTransaction().replace(R.id.container, new ShoppingCart()).commit();
                break;
            case R.id.find:
                fragmentManager.beginTransaction().replace(R.id.container, new Find()).commit();
                break;
            case R.id.my:
                fragmentManager.beginTransaction().replace(R.id.container, new Mine()).commit();
                break;
        }
    }
}
