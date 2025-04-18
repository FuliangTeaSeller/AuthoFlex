package com.fuliang.authoflex;

import com.fuliang.authoflex.excetion.NotLoginException;

/**
 * 对认证授权操作的封装。大部分情况下你只需要使用这个类
 */
public class AfUtil {
    public static AfLogic afLogic = new AfLogic();
    /**
     * 调用者自行验证账号密码匹配后，调用此方法通知框架
     * @param id 登录用户的id，推荐与数据库id一致
     */
    public static void login(String id) {
        afLogic.login(id);
    }

    /**
     * 登出，清除token
     */
    public static void logout() {
        afLogic.logout();
    }

    /**
     * 验证是否登录
     * @return boolean
     */
    public static boolean isLogin() {
        return afLogic.isLogin();
    }

    /**
     * 验证是否登录,未登录抛异常
     * @throws NotLoginException
     */
    public static void checkLogin() {
        if (!afLogic.isLogin()) {
            throw new NotLoginException();
        }
    }
}
