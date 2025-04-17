package com.fuliang.authoflex.servlet.model;

import com.fuliang.authoflex.context.AfRequest;

import javax.servlet.http.HttpServletRequest;

public class AfRequestForServlet implements AfRequest {
    private HttpServletRequest request;

    public AfRequestForServlet(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }
}
