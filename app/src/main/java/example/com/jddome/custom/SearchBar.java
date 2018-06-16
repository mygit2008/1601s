package example.com.jddome.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import example.com.jddome.R;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class SearchBar extends LinearLayout {
    private ImageView sao_iv, msg_iv;
    private LinearLayout sou_sou;

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.sousuo_layout, this);
        sao_iv = findViewById(R.id.sao_iv);
        sou_sou = findViewById(R.id.sou_sou);
        msg_iv = findViewById(R.id.msg_iv);
    }

    /**
     * 设置点击事件
     *
     * @param onclickListener
     */
    public void setSaoOnclickListener(OnClickListener onclickListener) {
        sao_iv.setOnClickListener(onclickListener);
    }

    public void setSouOnclickListener(OnClickListener onclickListener) {
        sou_sou.setOnClickListener(onclickListener);
    }

    public void setMsgOnclickListener(OnClickListener onclickListener) {
        msg_iv.setOnClickListener(onclickListener);
    }
}
