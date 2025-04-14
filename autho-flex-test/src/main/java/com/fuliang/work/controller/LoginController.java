package com.fuliang.work.controller;

import com.fuliang.authoflex.AfUtil;
import com.fuliang.work.common.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class LoginController {
    @PostMapping("/login")
    public AjaxResult login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        if (username.equals("admin") && password.equals("123456")) {
            AfUtil.login("admin");
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }
    @PostMapping("/logout")
    public AjaxResult logout() {
        AfUtil.logout();
        return AjaxResult.success();
    }
}
