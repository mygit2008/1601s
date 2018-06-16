package example.com.jddome.classify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import example.com.jddome.R;
import example.com.jddome.classify.bean.ClassifyBean;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class LeftClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ClassifyBean.DataBean> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LeftClassifyAdapter(Context context, List<ClassifyBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.leftclassify_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.fenlei_left.setText(data.get(position).getName());
        //设置字体颜色
        if (data.get(position).getChecked()) {
            myViewHolder.fenlei_left.setTextColor(Color.RED);
            myViewHolder.fenlei_left.setBackgroundColor(Color.GRAY);
        } else {
            myViewHolder.fenlei_left.setTextColor(Color.BLACK);
            myViewHolder.fenlei_left.setBackgroundColor(Color.WHITE);
        }
        myViewHolder.left_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fenlei_left;
        public LinearLayout left_classify;

        public MyViewHolder(View itemView) {
            super(itemView);
            fenlei_left = itemView.findViewById(R.id.fenlei_left);
            left_classify = itemView.findViewById(R.id.left_classify);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 选中后，改变字体颜色和背景色
     *
     * @param position
     * @param bool
     */
    public void changeCheck(int position, boolean bool) {
        //先还原一下
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setChecked(false);
        }
        ClassifyBean.DataBean dataBean = data.get(position);
        dataBean.setChecked(bool);
        //刷新界面
        notifyDataSetChanged();
    }
}
