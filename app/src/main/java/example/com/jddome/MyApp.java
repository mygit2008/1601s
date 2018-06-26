package example.com.jddome;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.autolayout.config.AutoLayoutConifg;

import example.com.jddome.greendao.DaoMaster;
import example.com.jddome.greendao.DaoSession;


/**
 * Created by zhangjunyou on 2018/6/7.
 */

public class MyApp extends Application {
    public static Context context;
    public static DaoSession daoSession;

    {
        PlatformConfig.setQQZone("1106987266", "ADfkMiRlA8Vrv9Gq");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);
        AutoLayoutConifg.getInstance().useDeviceSize();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        //初始化二维码jar包
        ZXingLibrary.initDisplayOpinion(this);
        //友盟初始化
        UMConfigure.init(this, "5b2b934e8f4a9d4c1b000014", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "ADfkMiRlA8Vrv9Gq");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        UMShareAPI.get(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
