package com.start.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.start.common.R;
import com.start.domain.Admins;
import com.start.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.LambdaConversionException;
import java.nio.charset.StandardCharsets;
import java.util.EmptyStackException;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author start
 * @since 2023-02-10
 */
@RestController
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    /**
     * 管理员登入
     * @param request
     * @param admins
     * @return
     */
    @PostMapping("/login")
    public R<Admins> login(HttpServletRequest request, @RequestBody Admins admins){

        //将页面密码进行md5加密
        String password=admins.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Admins> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admins::getUsername,admins.getUsername());
        Admins emp =adminsService.getOne(lambdaQueryWrapper);

        //如果没有查询到则返回登入失败
        if(emp==null){
            return R.error("登入失败");
        }

        //密码比对
        if(emp.getPassword().equals(password)){
            return R.error("登入失败");
        }

        //登入成功
        request.getSession().setAttribute("admins",emp.getUsername());

        return R.success(emp);

    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的员工的id
        request.getSession().removeAttribute("admins");
        return R.success("退出成功");
    }
}

