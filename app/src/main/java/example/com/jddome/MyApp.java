package example.com.jddome;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import example.com.jddome.greendao.DaoMaster;
import example.com.jddome.greendao.DaoSession;


/**
 * Created by zhangjunyou on 2018/6/7.
 */

public class MyApp extends Application {
    public static Context context;
    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }
}
