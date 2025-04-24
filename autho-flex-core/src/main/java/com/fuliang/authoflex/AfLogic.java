package com.fuliang.authoflex;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import com.fuliang.authoflex.exception.AfException;
import com.fuliang.authoflex.exception.NotLoginException;
import com.fuliang.authoflex.storage.AfDao;
import com.fuliang.authoflex.util.PrefixUtil;
//import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

//@Slf4j
public class AfLogic {
    private AuthoFlexConfig config;

    public AuthoFlexConfig getConfig() {
        return config;
    }

    public AuthoFlexConfig getAuthoFlexConfigOrGlobal() {
        AuthoFlexConfig localConfig = getConfig();
        if (localConfig != null) {
            return localConfig;
        }
        return AfManager.getAuthoFlexConfig();
    }

    public AfLogic setConfig(AuthoFlexConfig config) {
        this.config = config;
        return this;
    }

    public void login(String id) {
//        log.info(AfManager.getAuthoFlexConfig().getHelloMsg());
        String token = tryGenerateToken(id);

        tryInject(token);
    }

    private String tryGenerateToken(String id) {
        String token = getTokenById(id);
        if (token != null && !getAuthoFlexConfigOrGlobal().getTokenOverride()) {
            return token;
        }

        token = generateToken();

        //id -> token
        saveToken(id, token);
        //token -> id
        saveId(id, token);

        return token;
    }

    private void saveId(String id, String token) {
        AfManager.getAfDao().put(PrefixUtil.addTokenPrefix(token), id, getAuthoFlexConfigOrGlobal().getTokenTTLSeconds());
    }

    private void tryInject(String token) {
        AfManager.getAfContext().getResponse().setHeader("token", token);
    }

    public void logout() {
        AfDao dao = AfManager.getAfDao();
        String token = getTokenValueByContext();
        String id = dao.get(PrefixUtil.addTokenPrefix(token));

        dao.remove(PrefixUtil.addIdPrefix(id));
        dao.remove(PrefixUtil.addTokenPrefix(token));
    }

    private String getTokenValueByContext() {
        return AfManager.getAfContext().getRequest().getHeader(getAuthoFlexConfigOrGlobal().getTokenPrefix());
    }

    private void saveToken(String id, String token) {
        AfManager.getAfDao().put(PrefixUtil.addIdPrefix(id), token, getAuthoFlexConfigOrGlobal().getTokenTTLSeconds());
    }

    private String getTokenById(String id) {
        return AfManager.getAfDao().get(PrefixUtil.addIdPrefix(id));
    }

    private String generateToken() {
        String token = UUID.randomUUID().toString();
        while (AfManager.getAfDao().containsKey(PrefixUtil.addTokenPrefix(token))) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }

    public boolean isLoginById(String id) {
        return AfManager.getAfDao().containsKey(PrefixUtil.addIdPrefix(id));
    }

    public boolean isLoginByToken(String token) {
        return AfManager.getAfDao().containsKey(PrefixUtil.addTokenPrefix(token));
    }

    public void checkLoginById(String id) {
        if (!isLoginById(id)) {
            throw new AfException("未登录");
        }
    }

    public boolean isLogin() {
        return isLogin(tryGetLoginId());
    }

    public boolean isLogin(String id) {
        return getTokenById(id) != null;
    }

    public String tryGetLoginId() {
        String tokenPrefix = AfManager.getAuthoFlexConfig().getTokenPrefix();
        String token = AfManager.getAfContext().getRequest().getHeader(tokenPrefix);
        if (token == null) {
            return null;
        }
        return AfManager.getAfDao().get(PrefixUtil.addTokenPrefix(token));
    }

    public void checkLogin() {
        checkLogin(tryGetLoginId());
    }
    public void checkLogin(String id) {
        if(!isLogin(id)) {
            throw new NotLoginException();
        }
    }
}
