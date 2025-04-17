package com.fuliang.authoflex;

import com.fuliang.authoflex.config.AuthoFlexConfig;
import com.fuliang.authoflex.exception.AfLoginException;
import com.fuliang.authoflex.storage.AfDao;
import com.fuliang.authoflex.util.PrefixUtil;
//import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

//@Slf4j
public class AfLogic {
    private AuthoFlexConfig authoFlexConfig;

    public AuthoFlexConfig getAuthoFlexConfig() {
        return authoFlexConfig;
    }

    public AuthoFlexConfig getAuthoFlexConfigOrGlobal() {
        AuthoFlexConfig localConfig = getAuthoFlexConfig();
        if (localConfig != null) {
            return localConfig;
        }
        return AfManager.getAuthoFlexConfig();
    }

    public AfLogic setAuthoFlexConfig(AuthoFlexConfig authoFlexConfig) {
        this.authoFlexConfig = authoFlexConfig;
        return this;
    }

    public void login(String id) {
//        log.info(AfManager.getAuthoFlexConfig().getHelloMsg());
        String token = tryGenerateToken(id);

        tryInject(token);
    }

    private String tryGenerateToken(String id) {
        String token = getToken(id);
        if (token != null) {
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
        AfManager.getAfDao().put(PrefixUtil.addTokenPrefix(token), id);
    }

    private void tryInject(String token) {
        AfManager.getAfContext().getResponse().setHeader("token", token);
    }

    public void logout() {
        AfDao dao = AfManager.getAfDao();
        String token = getTokenValueByContext();
        String id = (String) dao.get(PrefixUtil.addTokenPrefix(token));

        dao.remove(PrefixUtil.addIdPrefix(id));
        dao.remove(PrefixUtil.addTokenPrefix(token));
    }

    private String getTokenValueByContext() {
        return AfManager.getAfContext().getRequest().getHeader(getAuthoFlexConfigOrGlobal().getTokenPrefix());
    }

    private void saveToken(String id, String token) {
        AfManager.getAfDao().put(PrefixUtil.addIdPrefix(id), token);
    }

    private String getToken(String id) {
        return (String) AfManager.getAfDao().get(PrefixUtil.addIdPrefix(id));
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
            throw new AfLoginException("未登录");
        }
    }

    public boolean isLogin() {
        return tryGetLoginId() != null;
    }

    public String tryGetLoginId() {
        String tokenPrefix = AfManager.getAuthoFlexConfig().getTokenPrefix();
        String header = AfManager.getAfContext().getRequest().getHeader(tokenPrefix);
        if (header == null) {
            return null;
        }
        return (String) AfManager.getAfDao().get(PrefixUtil.addTokenPrefix(header));
    }
}
