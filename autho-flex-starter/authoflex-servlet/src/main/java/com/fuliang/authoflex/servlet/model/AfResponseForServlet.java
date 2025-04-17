package com.fuliang.authoflex.servlet.model;

import com.fuliang.authoflex.context.AfRequest;
import com.fuliang.authoflex.context.AfResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfResponseForServlet implements AfResponse {
    private HttpServletResponse response;

    public AfResponseForServlet(HttpServletResponse response) {
        this.response = response;
    }


    @Override
    public void setHeader(String name, String value) {
        response.setHeader(name, value);
    }
}
