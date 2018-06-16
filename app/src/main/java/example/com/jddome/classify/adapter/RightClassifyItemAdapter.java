package example.com.jddome.classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.classify.ListProduct;
import example.com.jddome.classify.bean.ClassifyChildBean;

/**
 * @author zhangjunyou
 * @date 2018/6/14
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class RightClassifyItemAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassifyChildBean.DataBean> data;
    private LayoutInflater inflater;

    public RightClassifyItemAdapter(Context context, List<ClassifyChildBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.right_item_layout, parent, false);
        RightItemViewHolder holder = new RightItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RightItemViewHolder viewHolder = (RightItemViewHolder) holder;
        final List<ClassifyChildBean.DataBean.ListBean> list = data.get(position).getList();
        viewHolder.sname.setText(data.get(position).getName());
        viewHolder.right_class_item.setLayoutManager(new GridLayoutManager(MyApp.context, 3, RecyclerView.VERTICAL, false));
        Right_ItemAdapter right_itemAdapter = new Right_ItemAdapter(MyApp.context, list);
        viewHolder.right_class_item.setAdapter(right_itemAdapter);
        right_itemAdapter.setOnItemClickListener(new Right_ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //点击跳转到列表页面
                Intent intent = new Intent(MyApp.context, ListProduct.class);
                int pscid = list.get(position).getPscid();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pscid", pscid);
                Log.e("pscid-------->",pscid+"");
                MyApp.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class RightItemViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        RecyclerView right_class_item;

        public RightItemViewHolder(View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.sname);
            right_class_item = itemView.findViewById(R.id.right_class_item);
        }
    }
}
