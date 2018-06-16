package example.com.jddome.homepage.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import example.com.base.BaseActivity;
import example.com.base.mvp.BaseModel;
import example.com.jddome.R;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import example.com.jddome.homepage.model.ProductDetailModel;
import example.com.jddome.homepage.presenter.ProductDetailPresenter;

public class ProductDetail extends BaseActivity<ProductDetailPresenter> implements IGetProductDetailView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.newPrice)
    TextView mNewPrice;
    @BindView(R.id.addCar)
    Button mAddCar;
    @BindView(R.id.selCar)
    Button selCar;
    private int pid;
    private int uid = 4905;
    private int id;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData() {
        //获取JavaBean
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);
        pid = intent.getIntExtra("pid", 1);
        presenter.getProductDetail(pid + "");
    }

    @Override
    protected ProductDetailPresenter initPresenter() {
        return new ProductDetailPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new ProductDetailModel();
    }

    @OnClick({R.id.back, R.id.selCar, R.id.addCar})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.addCar:

                break;
        }
    }

    @Override
    public void success(GetProductDetail getProductDetail) {
        GetProductDetail.DataBean data = getProductDetail.getData();
        String[] split = data.getImages().split("\\|");
        List<String> imges = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            imges.add(split[i]);
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageURI(Uri.parse((String) path));
            }

            @Override
            public ImageView createImageView(Context context) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                return simpleDraweeView;
            }
        });
        mBanner.setImages(imges);
        mBanner.start();
        mTitle.setText(data.getTitle());
        mPrice.setText(data.getPrice() + "");
        mNewPrice.setText(data.getBargainPrice() + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        //解除注册
        EventBus.getDefault().unregister(this);
    }
}
