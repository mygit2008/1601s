package example.com.jddome.shoppingcart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.com.base.BaseFragment;
import example.com.base.mvp.BaseModel;
import example.com.jddome.MyApp;
import example.com.jddome.R;
import example.com.jddome.mycenter.login.LoginActivity;
import example.com.jddome.shoppingcart.adapter.ShopcartAdapter;
import example.com.jddome.shoppingcart.bean.DeleteCartBean;
import example.com.jddome.shoppingcart.bean.GoodsInfo;
import example.com.jddome.shoppingcart.bean.Selectshop;
import example.com.jddome.shoppingcart.bean.StoreInfo;
import example.com.jddome.shoppingcart.model.ShopCartModel;
import example.com.jddome.shoppingcart.presenter.CartPresenter;
import example.com.jddome.shoppingcart.view.ICartView;

/**
 * @author zhangjunyou
 * @date 2018/6/12
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class ShoppingCart extends BaseFragment<CartPresenter> implements ICartView, View.OnClickListener, ShopcartAdapter.CheckInterface,
        ShopcartAdapter.ModifyCountInterface {
    private View view;
    private ImageView back;
    /**
     * 购物车
     */
    private TextView title;
    private ExpandableListView exListView;
    /**
     * 全选
     */
    private CheckBox allChekbox;
    /**
     * ￥0.00
     */
    private TextView tvTotalPrice;
    /**
     * 结算(0)
     */
    private TextView tvGoToPay;
    // 组元素数据列表
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();
    // 子元素数据列表
    private Map<String, List<GoodsInfo>> children = new HashMap<String, List<GoodsInfo>>();
    private List<GoodsInfo> products;
    private ShopcartAdapter selva;
    // 购买的商品总价
    private double totalPrice = 0.00;
    // 购买的商品总数量
    private int totalCount = 0;
    private SharedPreferences sp;
    private LinearLayout yh;
    private SimpleDraweeView head_po;
    private TextView name;

    @Override
    protected void initView(View view) {
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) view.findViewById(R.id.title);
        exListView = (ExpandableListView) view.findViewById(R.id.exListView);
        allChekbox = (CheckBox) view.findViewById(R.id.all_chekbox);
        allChekbox.setOnClickListener(this);
        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        tvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        tvGoToPay.setOnClickListener(this);
        yh = (LinearLayout) view.findViewById(R.id.yh);
        head_po = (SimpleDraweeView) view.findViewById(R.id.head_po);
        name = (TextView) view.findViewById(R.id.name);
    }

    @Override
    protected BaseModel initModel() {
        return new ShopCartModel();
    }

    @Override
    protected CartPresenter initPresenter() {
        return new CartPresenter();
    }

    @Override
    protected int getLayoutid() {
        return R.layout.shoppingcar;
    }

    @Override
    protected void initData() {
        head_po.setImageURI(Uri.parse(sp.getString("iconur", "头像")));
        name.setText(sp.getString("screenname", "昵称"));
        if (sp.getString("token", "") != null) {
            exListView.setVisibility(View.VISIBLE);
        } else {
            exListView.setVisibility(View.INVISIBLE);
        }
        presenter.selectCart(sp.getString("uid", "4905"));
    }

    @Override
    public void success(Selectshop selectshop) {
        if (selectshop.getData() != null) {
            List<Selectshop.DataBean> data = selectshop.getData();
            for (int i = 0; i < data.size(); i++) {
                groups.add(new StoreInfo(data.get(i).getSellerid(), data.get(i).getSellerName()));
                products = new ArrayList<GoodsInfo>();
                for (int j = 0; j < data.get(i).getList().size(); j++) {
                    String images = data.get(i).getList().get(j).getImages();
                    String[] split = images.split("[|]");
                    String[] split1 = split[0].split("[!]");
                    List<Selectshop.DataBean.ListBean> list = data.get(i).getList();
                    products.add(new GoodsInfo(list.get(j).getPid() + "", "商品", list.get(j).getTitle(), Double.valueOf(list.get(j).getPrice() + ""), Integer.parseInt(list.get(j).getNum() + ""), "", "1", split1[0], Double.valueOf(list.get(j).getBargainPrice())));
                }
                // 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                children.put(groups.get(i).getId(), products);
            }
            selva = new ShopcartAdapter(groups, children, MyApp.context);
            selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
            selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
            exListView.setAdapter(selva);
            //默认展开
            for (int i = 0; i < selva.getGroupCount(); i++) {
                // 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                exListView.expandGroup(i);
            }
        }
    }

    @Override
    public void delSuccess(DeleteCartBean deleteCartBean) {
        Toast.makeText(MyApp.context, deleteCartBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                getActivity().finish();
                break;
            case R.id.tv_go_to_pay:
                AlertDialog alert = new AlertDialog.Builder(MyApp.context).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (totalCount == 0) {
                                    Toast.makeText(MyApp.context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        });
                alert.show();
                break;
            case R.id.all_chekbox:
                doCheckAll();
                break;
        }
    }

    /**
     * 全选反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        //调用计算总数量与总价方法
        calculate();
        //刷新
        selva.notifyDataSetChanged();
    }

    /**
     * 计算总数量与总价
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getDiscountPrice() * product.getCount();
                }
            }
        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("去支付(" + totalCount + ")");
    }

    /**
     * 判断所有组选框状态是否全部选中
     *
     * @return
     */
    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 组选框状态改变触发的事件
     *
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        // 判断改组下面的所有子元素是否是同一种状态
        boolean allChildSameState = true;
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            // 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
            group.setChoosed(isChecked);
        } else {
            // 否则，组元素一律设置为未选中状态
            group.setChoosed(false);
        }
        if (isAllCheck()) {
            // 全选
            allChekbox.setChecked(true);
        } else {
            // 反选
            allChekbox.setChecked(false);
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        calculate();
        selva.notifyDataSetChanged();
    }

    /**
     * 删减操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsInfo product = (GoodsInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1) {
            Toast.makeText(MyApp.context, "数量不能少于1", Toast.LENGTH_SHORT).show();
            return;
        }
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        calculate();
        selva.notifyDataSetChanged();
    }

    /**
     * 删除子item
     *
     * @param groupPosition
     * @param childPosition
     */
    @Override
    public void childDelete(final int groupPosition, final int childPosition, int pid) {
        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo> childs = children.get(group.getId());
        if (childs.size() == 0) {
            presenter.deleteCart(sp.getString("uid", "1"), pid);
            Log.e("del----pid", pid + "");
            groups.remove(groupPosition);
        }
        selva.notifyDataSetChanged();
    }
}
