package example.com.jddome.homepage.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
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
import example.com.jddome.MainActivity;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.bean.ListProductBean;
import example.com.jddome.homepage.bean.AddCartBean;
import example.com.jddome.homepage.bean.GetAdHomeBean;
import example.com.jddome.homepage.bean.GetProductDetail;
import example.com.jddome.homepage.model.ProductDetailModel;
import example.com.jddome.homepage.presenter.ProductDetailPresenter;
import example.com.jddome.mycenter.login.LoginActivity;

public class ProductDetail extends BaseActivity<ProductDetailPresenter> implements IGetProductDetailView {

    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.ivShare)
    ImageView ivShare;
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
    private GetProductDetail.DataBean data;
    private SharedPreferences sp;
    //分享的监听
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ProductDetail.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ProductDetail.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ProductDetail.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        //获取JavaBean
        Intent intent = getIntent();
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

    @OnClick({R.id.back, R.id.selCar, R.id.ivShare, R.id.addCar})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.ivShare:
                //分享
                UMWeb umWeb = new UMWeb(data.getDetailUrl());
                new ShareAction(ProductDetail.this).withMedia(umWeb).setDisplayList(SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
            case R.id.selCar:
                if (sp.getString("token", "") == null) {
                    Intent intent1 = new Intent(MyApp.context, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                    intent.putExtra("id", 2);
                    startActivity(intent);
                }
                break;
            case R.id.addCar:
                if (sp.getString("token", "") == null) {
                    Intent intent1 = new Intent(MyApp.context, LoginActivity.class);
                    startActivity(intent1);
                }
                presenter.addCart(sp.getString("uid", "0"), pid);
                break;
        }
    }

    @Override
    public void success(GetProductDetail getProductDetail) {
        data = getProductDetail.getData();
        if (data == null) {
            return;
        }
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
    public void addSuccess(AddCartBean addCartBean) {
        Toast.makeText(MyApp.context, "添加成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
        //解除注册
        EventBus.getDefault().unregister(this);
    }
}
