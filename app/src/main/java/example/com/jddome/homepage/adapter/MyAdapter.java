package example.com.jddome.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.jddome.R;
import example.com.jddome.homepage.bean.Goods;


public class MyAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<Goods> select;
    private Context context;

    public MyAdapter(List<Goods> select, Context context) {
        this.select = select;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Goods goods = select.get(position);
        myViewHolder.tv_item1.setText(goods.getGname());
    }

    @Override
    public int getItemCount() {
        return select.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_item1;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item1 = itemView.findViewById(R.id.tv_item1);
        }
    }
}