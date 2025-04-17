package com.fuliang.authoflex.spring;

import com.fuliang.authoflex.context.AfContext;
import com.fuliang.authoflex.context.AfRequest;
import com.fuliang.authoflex.context.AfResponse;
import com.fuliang.authoflex.servlet.model.AfRequestForServlet;
import com.fuliang.authoflex.servlet.model.AfResponseForServlet;

public class AfContextForSpring implements AfContext {
    @Override
    public AfRequest getRequest() {
        return new AfRequestForServlet(SpringMVCUtil.getRequest());
    }

    @Override
    public AfResponse getResponse() {
        return new AfResponseForServlet(SpringMVCUtil.getResponse());
    }
}
