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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import example.com.jddome.R;
import example.com.jddome.classify.bean.SeekBean;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class Seekdapter extends XRecyclerView.Adapter {
    private Context context;
    private List<SeekBean.DataBean> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Seekdapter(Context context, List<SeekBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.showproducts, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        productViewHolder.goodLog.setImageURI(data.get(position).getImages().split("\\|")[0]);
        productViewHolder.title.setText(data.get(position).getTitle());
        productViewHolder.price.setText("原价：" + data.get(position).getPrice());
        productViewHolder.bargainPrice.setText("优惠价：" + data.get(position).getBargainPrice());
        productViewHolder.showProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class ProductViewHolder extends XRecyclerView.ViewHolder {
        LinearLayout showProduct;
        SimpleDraweeView goodLog;
        TextView title, price, bargainPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            showProduct = itemView.findViewById(R.id.showProduct);
            goodLog = itemView.findViewById(R.id.goodLog);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            bargainPrice = itemView.findViewById(R.id.bargainPrice);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, SeekBean.DataBean dataBean);
    }
}
