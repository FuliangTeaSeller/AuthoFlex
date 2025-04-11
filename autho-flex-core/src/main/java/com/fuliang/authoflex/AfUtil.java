package com.fuliang.authoflex;
public class AfUtil {
    public static AfLogic afLogic = new AfLogic();
    /**
     * 调用者自行验证密码匹配后，调用此方法通知框架
     * @param id 登录用户的id，推荐与数据库id一致
     */
    public static void login(String id) {
        afLogic.login(id);
    }
    public static void logout() {
        afLogic.logout();
    }
}
