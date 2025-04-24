package com.fuliang.authoflex.annotation.handler;

import com.fuliang.authoflex.AfManager;
import com.fuliang.authoflex.AfUtil;
import com.fuliang.authoflex.annotation.AfCheckMode;
import com.fuliang.authoflex.annotation.AfCheckPermission;
import com.fuliang.authoflex.exception.NoPermissionException;

import java.util.List;

public class AfCheckPermissionHandler implements AfAnnotationProcessHandler<AfCheckPermission> {

    @Override
    public void process(AfCheckPermission annotation) {
        String loginId = AfUtil.getLoginId();
        AfUtil.checkLogin(loginId);

        List<String> permission = AfManager.getAfPermissionHanlder().getPermission(loginId);
        String[] requiredPermission = annotation.value();
        AfCheckMode checkMode = annotation.mode();

        if (checkMode.equals(AfCheckMode.AND)) {
            for (String p : requiredPermission) {
                if (!permission.contains(p)) {
                    throw new NoPermissionException(String.format("权限验证不通过,模式: %s,需要权限: %s", checkMode, p));
                }
            }
        } else if (checkMode.equals(AfCheckMode.OR)) {
            for (String p : requiredPermission) {
                if (permission.contains(p)) {
                    return;
                }
            }
            throw new NoPermissionException(String.format("权限验证不通过,模式: %s,需要权限: %s", checkMode, permission));
        }
    }
}
