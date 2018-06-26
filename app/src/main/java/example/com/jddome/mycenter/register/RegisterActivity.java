package example.com.jddome.mycenter.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.base.BaseActivity;
import example.com.base.mvp.BaseModel;
import example.com.base.mvp.BasePresenter;
import example.com.jddome.R;
import example.com.jddome.mycenter.login.LoginActivity;
import example.com.jddome.mycenter.register.bean.RegBean;
import example.com.jddome.mycenter.register.model.RegModel;
import example.com.jddome.mycenter.register.presenter.RegPresenter;
import example.com.jddome.mycenter.register.view.IRegister;

public class RegisterActivity extends BaseActivity<RegPresenter> implements View.OnClickListener, IRegister {

    /**
     * 请输入手机号
     */
    private EditText etMobile;
    /**
     * 请输入密码
     */
    private EditText etPwd;
    /**
     * 立即注册
     */
    private Button regBtn;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    public void initView() {
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegPresenter initPresenter() {
        return new RegPresenter();
    }

    @Override
    protected BaseModel initModel() {
        return new RegModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.reg_btn:
                String mobile = etMobile.getText().toString();
                String pwd = etPwd.getText().toString();
                presenter.register(mobile, pwd);
                break;
        }
    }

    @Override
    public void success(RegBean regBean) {
        Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(it);
    }
}
