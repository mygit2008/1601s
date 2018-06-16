package example.com.jddome.homepage.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class ClassifyAdapter extends RecyclerView.Adapter {
    private List<GetAdHomeBean.DataBean.FenleiBean> fenlei;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassifyAdapter(List<GetAdHomeBean.DataBean.FenleiBean> fenlei, Context context) {
        this.fenlei = fenlei;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fenlei_layout, parent, false);
        MyViewHolder classViewHolder = new MyViewHolder(view);
        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //绑定holder，显示数据
        MyViewHolder classViewHolder = (MyViewHolder) holder;
        final GetAdHomeBean.DataBean.FenleiBean fenleiBean = fenlei.get(position);
        classViewHolder.img.setImageURI(fenleiBean.getIcon());
//        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(classViewHolder.img);
        classViewHolder.tv.setText(fenleiBean.getName() + "");
        //设置条目点击
        classViewHolder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, fenleiBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fenlei.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv;
        public final LinearLayout rlayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.class_img);
            tv = itemView.findViewById(R.id.class_title);
            rlayout = itemView.findViewById(R.id.class_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, GetAdHomeBean.DataBean.FenleiBean fenleiBean);
    }

}
