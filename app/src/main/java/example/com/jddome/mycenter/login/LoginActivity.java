package example.com.jddome.mycenter.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;
import example.com.base.BaseActivity;
import example.com.base.mvp.BaseModel;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.mycenter.login.bean.LoginBean;
import example.com.jddome.mycenter.login.model.LoginModel;
import example.com.jddome.mycenter.login.presenter.LoginPresenter;
import example.com.jddome.mycenter.login.view.ILoginView;
import example.com.jddome.mycenter.register.RegisterActivity;


public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, ILoginView {

    /**
     * X
     */
    private TextView back;
    /**
     * 客服
     */
    private TextView ser;
    /**
     * 账号密码登录
     */
    private RadioButton idlogingTv;
    /**
     * 手机号快捷登录
     */
    private RadioButton telloginTv;
    /**
     * 请输入手机号
     */
    private EditText mobile;
    /**
     * 请输入密码
     */
    private EditText password;
    /**
     * 登录
     */
    private Button loginBtn;
    /**
     * 忘记密码
     */
    private TextView alter;
    /**
     * 新用户注册
     */
    private TextView regTv1;
    private LinearLayout loginL1;
    /**
     * 请输入手机号
     */
    private EditText mobile2;
    /**
     * 获取验证码
     */
    private Button authcodeBtn;
    /**
     * 新用户注册
     */
    private TextView regTv2;
    private EditText yanz;
    private Button mob_login;
    private LinearLayout loginL2;
    private ImageView qqBtn;
    private ImageView weixinBtn;
    //监听事件
    private UMAuthListener umAuthListener;
    private SharedPreferences sp;
    private Bundle bundle;
    private String phone;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    public void initView() {
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        back = (TextView) findViewById(R.id.back);
        back.setOnClickListener(this);
        ser = (TextView) findViewById(R.id.ser);
        idlogingTv = (RadioButton) findViewById(R.id.idloging_tv);
        idlogingTv.setOnClickListener(this);
        telloginTv = (RadioButton) findViewById(R.id.tellogin_tv);
        telloginTv.setOnClickListener(this);
        mobile = (EditText) findViewById(R.id.mobile);
        yanz = (EditText) findViewById(R.id.yanz);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
        mob_login = (Button) findViewById(R.id.mob_login);
        mob_login.setOnClickListener(this);
        alter = (TextView) findViewById(R.id.alter);
        regTv1 = (TextView) findViewById(R.id.reg_tv1);
        regTv1.setOnClickListener(this);
        loginL1 = (LinearLayout) findViewById(R.id.login_l1);
        mobile2 = (EditText) findViewById(R.id.mobile2);
        authcodeBtn = (Button) findViewById(R.id.authcode_btn);
        authcodeBtn.setOnClickListener(this);
        regTv2 = (TextView) findViewById(R.id.reg_tv2);
        regTv2.setOnClickListener(this);
        loginL2 = (LinearLayout) findViewById(R.id.login_l2);
        qqBtn = (ImageView) findViewById(R.id.qq_btn);
        qqBtn.setOnClickListener(this);
        weixinBtn = (ImageView) findViewById(R.id.weixin_btn);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);

    }

    @Override
    protected void initData() {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(MyApp.context).setShareConfig(config);
        umAuthListener = new UMAuthListener() {
            @Override//授权成功回调获取用户信息
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Set<String> keySet = map.keySet();
                Log.e("tag------------>", keySet.toString());
                bundle = new Bundle();
                sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                for (String string : keySet) {
                    if (string.equals("access_token")) {
                        String access_token = map.get("access_token");
                        bundle.putString("token", access_token);
                        //通过editor对象写入数据
                        edit.putString("token", access_token);
                        //提交数据存入到xml文件中
                        edit.commit();
                    }
                    if (string.equals("city")) {
                        String city = map.get("city");
                        bundle.putString("city", city);
                        //通过editor对象写入数据
                        edit.putString("city", city);
                        //提交数据存入到xml文件中
                        edit.commit();
                    }
                    if (string.equals("screen_name")) {
                        //获取登录的名字//得到昵称
                        String screenname = map.get("screen_name");
                        bundle.putString("screenname", screenname);
                        //通过editor对象写入数据
                        edit.putString("screenname", screenname);
                        //提交数据存入到xml文件中
                        edit.commit();
                    }
                    if (string.equals("profile_image_url")) {
                        //获取登录的图片//得到头像
                        String imageUrl = map.get("profile_image_url");
                        bundle.putString("iconurl", imageUrl);
                        //通过editor对象写入数据
                        edit.putString("iconur", imageUrl);
                        //提交数据存入到xml文件中
                        edit.commit();
                    }
                }
                if (bundle != null) {
                    edit.putBoolean("falg", true);
                    edit.commit();
                    setResult(RESULT_CANCELED, LoginActivity.this.getIntent().putExtras(bundle));
                    finish();
                }
            }

            @Override//授权失败回调
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(LoginActivity.this, "Authorize fail", Toast.LENGTH_SHORT).show();
            }

            @Override//SHARE_MEDIA  可以判断授权的第三方是什么
            public void onStart(SHARE_MEDIA share_media) {
            }

            @Override//取消授权回调
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(LoginActivity.this, "Authorize cancel", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new LoginModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.mob_login:
                SMSSDK.submitVerificationCode("86", phone, yanz.getText().toString());
                break;
            case R.id.reg_tv1:
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
                break;
            case R.id.reg_tv2:
                Intent it1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it1);
                break;
            case R.id.idloging_tv:
                loginL1.setVisibility(View.VISIBLE);
                loginL2.setVisibility(View.GONE);
                break;
            case R.id.tellogin_tv:
                loginL1.setVisibility(View.GONE);
                loginL2.setVisibility(View.VISIBLE);
                break;
            case R.id.login_btn:
                String mob = mobile.getText().toString();
                String pwd = password.getText().toString();
                presenter.login(mob, pwd);
                break;
            case R.id.authcode_btn:
                SMSSDK.getVerificationCode("86", mobile2.getText().toString());
                phone = mobile2.getText().toString();
                break;
            case R.id.qq_btn:
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result" + event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
//                    textV.setText(data.toString());
                }

            } else {
//              ((Throwable) data).printStackTrace();
//              Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                  Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(MyApp.context, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }
        }
    };

    @Override
    public void loginSuccess(LoginBean loginBean) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("uid", loginBean.getData().getUid() + "");
        edit.putString("token", loginBean.getData().getToken() + "");
        edit.putString("iconur", loginBean.getData().getIcon());
        edit.putString("screenname", loginBean.getData().getUsername() + "");
        edit.putBoolean("falg", false);
        edit.commit();
        LoginActivity.this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
