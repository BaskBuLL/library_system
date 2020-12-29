package com.baskbull.library_system.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baskbull.library_system.common.lang.Result;
import com.baskbull.library_system.common.lang.dto.LoginDto;
import com.baskbull.library_system.entity.Reader;
import com.baskbull.library_system.service.ReaderService;
import com.baskbull.library_system.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 * @author baskbull
 */
@RestController
public class AccountController {

    @Autowired
    ReaderService readerService;

    @Autowired
    JwtUtil jwtUtil;

    /**
     * 从body中获取loginDto
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        Reader reader = readerService.getOne(new QueryWrapper<Reader>().eq("rd_id", loginDto.getRdId()));
        Assert.notNull(reader,"用户不存在");

        if(!reader.getRdPwd().equals(SecureUtil.md5(loginDto.getRdPwd()))){
            return Result.error("密码不正确");
        }
        String jwt = jwtUtil.generateToken(reader.getRdId());
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        return Result.ok(JSONObject.toJSON(reader));
    }

//    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.ok();
    }
}
