package com.fuliang.authoflex.permission;

import java.util.List;

public interface AfPermissionHanlder {
    List<String> getPermission(String id);
    List<String> getRole(String id);
}
