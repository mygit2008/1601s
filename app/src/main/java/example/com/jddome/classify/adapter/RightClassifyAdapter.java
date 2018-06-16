package example.com.jddome.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.bean.ClassifyBean;
import example.com.jddome.classify.bean.ClassifyChildBean;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RightClassifyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassifyChildBean.DataBean> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RightClassifyAdapter(Context context, List<ClassifyChildBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rightclassify_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.class_right.setLayoutManager(new LinearLayoutManager(MyApp.context));
        myViewHolder.class_right.setAdapter(new RightClassifyItemAdapter(MyApp.context,data));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView class_right;
        public SimpleDraweeView simpleDraweeView;

        public MyViewHolder(View itemView) {
            super(itemView);
            class_right = itemView.findViewById(R.id.class_right);
            simpleDraweeView = itemView.findViewById(R.id.log);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
