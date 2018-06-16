package example.com.jddome.homepage.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.homepage.view.ProductDetail;
import example.com.jddome.homepage.bean.GetAdHomeBean;

/**
 * @author zhangjunyou
 * @date 2018/6/9
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class GetHomeAdapter extends RecyclerView.Adapter {
    private Context context;
    GetAdHomeBean.DataBean data;
    private static final int TYPE_ONE = 0;   //item的显示样式
    private static final int TYPE_TWO = 1;   //item的显示样式
    private static final int TYPE_THREE = 2;   //item的显示样式
    private static final int TYPE_FOUR = 3;   //item的显示样式
    private ArrayList<String> imges;
    private ViewHolder3 viewHolder3;
    /**
     * CountDownTimer 实现倒计时
     * 参数1，设置倒计时的总时间（毫秒）
     * 参数2，设置每次减去多少毫秒
     */
    private CountDownTimer countDownTimer;
    private long hour;

    public GetHomeAdapter(Context context, GetAdHomeBean.DataBean data) {
        this.context = context;
        this.data = data;
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        countDownTimer = new CountDownTimer(hour, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//            String value = String.valueOf((hour / 1000));
                //秒杀倒计时
                setTimeShow((millisUntilFinished / 1000), viewHolder3);
            }

            @Override
            public void onFinish() {
                //秒杀倒计时
//            setTime(viewHolder3);
//            setTimeShow((hour / 1000), viewHolder3);
            }
        };
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
            ViewHolder1 viewHolder1 = new ViewHolder1(view);
            return viewHolder1;
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_classify, parent, false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        } else if (viewType == TYPE_THREE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_miaosha, parent, false);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_tuijian, parent, false);
            ViewHolder4 viewHolder4 = new ViewHolder4(view);
            return viewHolder4;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //有可能会有多种Item的显示样式,所有在设置值之前要进行判断
        if (getItemViewType(position) == TYPE_ONE) {
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            List<GetAdHomeBean.DataBean.BannerBean> banner = data.getBanner();
            imges = new ArrayList<>();
            for (int i = 0; i < banner.size(); i++) {
                imges.add(banner.get(i).getIcon());
            }
            viewHolder1.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    imageView.setImageURI(Uri.parse((String) path));
                }

                @Override
                public ImageView createImageView(Context context) {
                    SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                    return simpleDraweeView;
                }
            });
            viewHolder1.banner.setImages(imges);
            viewHolder1.banner.start();
        } else if (getItemViewType(position) == TYPE_TWO) {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.classify.setLayoutManager(new GridLayoutManager(MyApp.context, 2, RecyclerView.HORIZONTAL, false));
            List<GetAdHomeBean.DataBean.FenleiBean> fenlei = data.getFenlei();
            ClassifyAdapter classifyAdapter = new ClassifyAdapter(fenlei, MyApp.context);
            viewHolder2.classify.setAdapter(classifyAdapter);
            return;
        } else if (getItemViewType(position) == TYPE_THREE) {
            viewHolder3 = (ViewHolder3) holder;
            viewHolder3.miaosha.setLayoutManager(new LinearLayoutManager(MyApp.context, LinearLayoutManager.HORIZONTAL, false));
            List<GetAdHomeBean.DataBean.MiaoshaBean.ListBean> miaos = data.getMiaosha().getList();
            MiaoshaAdapter miaoshaAdapter = new MiaoshaAdapter(miaos, MyApp.context);
            viewHolder3.miaosha.setAdapter(miaoshaAdapter);
            miaoshaAdapter.setmOnRecyclerviewItemClickListener(new MiaoshaAdapter.OnRecyclerviewItemClickListener() {
                @Override
                public void onItemClickListener(int position, GetAdHomeBean.DataBean.MiaoshaBean.ListBean listBean) {
                    Intent it = new Intent(MyApp.context, ProductDetail.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                    EventBus.getDefault().post(listBean);
                }
            });
            setTime(viewHolder3);
            countDownTimer.start();
        } else {
            ViewHolder4 viewHolder4 = (ViewHolder4) holder;
            List<GetAdHomeBean.DataBean.TuijianBean.ListBeanX> tuijian = data.getTuijian().getList();
            viewHolder4.tuijian.setLayoutManager(new GridLayoutManager(MyApp.context, 2));
            TuijianAdapter tuijianAdapter = new TuijianAdapter(tuijian, MyApp.context);
            viewHolder4.tuijian.setAdapter(tuijianAdapter);
            tuijianAdapter.setOnItemClickListener(new TuijianAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, GetAdHomeBean.DataBean.TuijianBean.ListBeanX listBeanX) {
                    Intent it = new Intent(MyApp.context, ProductDetail.class);
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                    EventBus.getDefault().post(listBeanX);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        //可能根据数据的字段来指定对应的type
        if (position == 0) {
            return TYPE_ONE;
        } else if (position == 1) {
            return TYPE_TWO;
        } else if (position == 2) {
            return TYPE_THREE;
        } else {
            return TYPE_FOUR;
        }
    }

    @Override
    public int getItemCount() {
        return data.getBanner().size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {

        private Banner banner;

        public ViewHolder1(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {

        private RecyclerView classify;

        public ViewHolder2(View itemView) {
            super(itemView);
            classify = itemView.findViewById(R.id.classify);
        }
    }

    class ViewHolder3 extends RecyclerView.ViewHolder {
        private RecyclerView miaosha;
        private TextView mTvMiaoshaTime;
        private TextView mTvMiaoshaShi;
        private TextView mTvMiaoshaMinter;
        private TextView mTvMiaoshaSecond;

        public ViewHolder3(View itemView) {
            super(itemView);
            miaosha = itemView.findViewById(R.id.miaosha);
            mTvMiaoshaTime = (TextView) itemView.findViewById(R.id.tv_miaosha_time);
            mTvMiaoshaShi = (TextView) itemView.findViewById(R.id.tv_miaosha_shi);
            mTvMiaoshaMinter = (TextView) itemView.findViewById(R.id.tv_miaosha_minter);
            mTvMiaoshaSecond = (TextView) itemView.findViewById(R.id.tv_miaosha_second);
        }
    }

    class ViewHolder4 extends RecyclerView.ViewHolder {
        private RecyclerView tuijian;

        public ViewHolder4(View itemView) {
            super(itemView);
            tuijian = itemView.findViewById(R.id.tuijian);
        }
    }

    private void setTime(ViewHolder3 holder) {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
//        setTimeShow(hour, holder);
    }

    private void setTimeShow(long hour, ViewHolder3 holder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date useTime = new Date(System.currentTimeMillis());
        String format = df.format(useTime);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        // Log.d("ccc", substring);
        if (hour % 2 == 0) {
            holder.mTvMiaoshaTime.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            holder.mTvMiaoshaTime.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(totime);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            holder.mTvMiaoshaShi.setText("0" + hours + "");
            if (minute >= 10) {
                holder.mTvMiaoshaMinter.setText(minute + "");
            } else {
                holder.mTvMiaoshaMinter.setText("0" + minute + "");
            }
            if (second >= 10) {
                holder.mTvMiaoshaSecond.setText(second + "");
            } else {
                holder.mTvMiaoshaSecond.setText("0" + second + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        handler.sendEmptyMessage(0);
    }
}
