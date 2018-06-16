package example.com.jddome.homepage.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import example.com.jddome.R;
import example.com.jddome.homepage.bean.GetAdHomeBean;


/**
 * Created by zhangjunyou on 2018/5/18.
 */

public class MiaoshaAdapter extends RecyclerView.Adapter {
    private List<GetAdHomeBean.DataBean.MiaoshaBean.ListBean> miaos;
    private Context context;
    private LayoutInflater inflater;

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener;

    public void setmOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener) {
        this.mOnRecyclerviewItemClickListener = mOnRecyclerviewItemClickListener;
    }

    public MiaoshaAdapter(List<GetAdHomeBean.DataBean.MiaoshaBean.ListBean> miaos, Context context) {
        this.miaos = miaos;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.miaosha_layout, parent, false);
        MyViewHolder classViewHolder = new MyViewHolder(view);
        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //绑定holder，显示数据
        MyViewHolder classViewHolder = (MyViewHolder) holder;
        final GetAdHomeBean.DataBean.MiaoshaBean.ListBean listBean = miaos.get(position);
        classViewHolder.img.setImageURI(listBean.getImages().split("\\|")[1]);
//        Glide.with(context).load(listBeanX.getImages().split("\\|")[1]).into(classViewHolder.img);
        classViewHolder.tv.setText(listBean.getPrice() + "");
        classViewHolder.price.setText(listBean.getBargainPrice() + "");
        //设置条目点击
        classViewHolder.llayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerviewItemClickListener != null) {
                    mOnRecyclerviewItemClickListener.onItemClickListener(position, listBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return miaos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv;
        private final LinearLayout llayout;
        private final TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.class_img);
            tv = itemView.findViewById(R.id.class_title);
            price = itemView.findViewById(R.id.class_price);
            llayout = itemView.findViewById(R.id.class_item);
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onItemClickListener(int position, GetAdHomeBean.DataBean.MiaoshaBean.ListBean listBean);
    }
}
