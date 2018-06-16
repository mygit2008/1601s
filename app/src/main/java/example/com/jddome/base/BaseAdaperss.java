package example.com.jddome.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhangjunyou on 2018/6/8.
 */

public abstract class BaseAdaperss<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<T> list;                       //数据
    IRecyclerItemChangeListerent mListerent;  //回调接口

    //设置数据的方法
    public void setData(ArrayList<T> data) {
        this.list = data;
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //抽象方法,子类出具体实现
        return getLayout(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        //抽象方法子类去具体实现
        setItemData(holder, position);
        //设置Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件回调到Activity
                if (mListerent != null) {
                    mListerent.onitemLister(v, position);
                }
            }
        });
    }

    //抽象方法,子类实现具体操作
    protected abstract void setItemData(RecyclerView.ViewHolder holder, int position);

    //布局
    protected abstract RecyclerView.ViewHolder getLayout(ViewGroup parent, int viewType);

    //设置点击事件借口回调
    public void setListerent(IRecyclerItemChangeListerent listerent) {
        mListerent = listerent;
    }

    //设置一个回调的接口
    interface IRecyclerItemChangeListerent {
        void onitemLister(View v, int postion);
    }
}
