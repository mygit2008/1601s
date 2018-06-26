package example.com.jddome.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import example.com.jddome.R;

public class WebViewActivity extends AppCompatActivity {

//    private WebView mWv;
    private String detailUrl;
    private TextView mStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        //接收地址
        Intent intent = getIntent();
        detailUrl = intent.getStringExtra("detailUrl");
        initView();
//        mWv.loadUrl(detailUrl);
    }

    private void initView() {
//        mWv = (WebView) findViewById(R.id.wv);
//        WebSettings settings = mWv.getSettings();
        mStr = (TextView) findViewById(R.id.str);
        mStr.setText(detailUrl);
    }
}
