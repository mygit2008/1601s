package example.com.jddome.homepage.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author zhangjunyou
 * @date 2018/6/15
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */
@Entity
public class Goods {
    private String gname;

    public String getGname() {
        return this.gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Generated(hash = 2061197572)
    public Goods(String gname) {
        this.gname = gname;
    }

    @Generated(hash = 1770709345)
    public Goods() {
    }

}
