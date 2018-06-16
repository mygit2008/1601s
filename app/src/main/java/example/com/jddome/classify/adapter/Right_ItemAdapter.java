package example.com.jddome.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import example.com.jddome.R;
import example.com.jddome.classify.bean.ClassifyChildBean;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class Right_ItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassifyChildBean.DataBean.ListBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Right_ItemAdapter(Context context, List<ClassifyChildBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_right_classfiy, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.class_img.setImageURI(list.get(position).getIcon());
        viewHolder.class_title.setText(list.get(position).getName());
        viewHolder.class_item.setOnClickListener(new View.OnClickListener() {
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
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout class_item;
        SimpleDraweeView class_img;
        TextView class_title;

        public ViewHolder(View itemView) {
            super(itemView);
            class_item = itemView.findViewById(R.id.class_item);
            class_img = itemView.findViewById(R.id.class_img);
            class_title = itemView.findViewById(R.id.class_title);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
