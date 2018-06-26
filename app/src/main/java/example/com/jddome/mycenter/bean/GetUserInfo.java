package example.com.jddome.mycenter.bean;

/**
 * @author zhangjunyou
 * @date 2018/6/21
 * @description
 * @Copyright 版权所有, 未经授权不得转载其他 .
 */

public class GetUserInfo {

    /**
     * code : 0
     * data : {"appkey":"3810e90242942ccc","appsecret":"CCC86F14A03DE8B7507F03584796B7D1","createtime":"2018-06-20T20:56:13","fans":0,"follow":0,"icon":"https://www.zhaoapi.cn/images/1522494152236aa.jpg","mobile":"13813575369","password":"8F669074CAF5513351A2DE5CC22AC04C","token":"F07AD4CFD507904EB3E05A3EEF5D9044","uid":4905,"username":"13813575369"}
     * msg : 获取用户信息成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * appkey : 3810e90242942ccc
         * appsecret : CCC86F14A03DE8B7507F03584796B7D1
         * createtime : 2018-06-20T20:56:13
         * fans : 0
         * follow : 0
         * icon : https://www.zhaoapi.cn/images/1522494152236aa.jpg
         * mobile : 13813575369
         * password : 8F669074CAF5513351A2DE5CC22AC04C
         * token : F07AD4CFD507904EB3E05A3EEF5D9044
         * uid : 4905
         * username : 13813575369
         */

        private String appkey;
        private String appsecret;
        private String createtime;
        private int fans;
        private int follow;
        private String icon;
        private String mobile;
        private String password;
        private String token;
        private int uid;
        private String username;

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
