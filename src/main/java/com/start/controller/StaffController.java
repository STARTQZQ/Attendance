package com.start.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.R;
import com.start.domain.Admins;
import com.start.domain.Staff;
import com.start.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 员工 前端控制器
 * </p>
 *
 * @author start
 * @since 2023-02-11
 */
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    //后台后端处理
    /**
     * 新增员工
     * @param staff
     * @return
     */
    @PostMapping
    public R<String> save(@DateTimeFormat(pattern = "yyyy-MM-dd HH") @RequestBody Staff staff){
        log.info("新增员工，员工信息{}",staff.toString());
        //密码要进行md5加密
        staff.setPassword(DigestUtils.md5DigestAsHex(staff.getPassword().getBytes()));
        staffService.save(staff);
        return R.success("新增员工成功");
    }

    /**
     * 员工数据查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String  name){
        log.info("page={} pageSize={} name={}",page,pageSize,name);

        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Staff> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Strings.isNotEmpty(name),Staff::getName,name);

        //添加排序条件

        queryWrapper.orderByDesc(Staff::getEntryTime);

        //执行查询
        staffService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Staff> getById(@PathVariable int id){
        log.info("感觉id查询员工信息");
        Staff staff=staffService.getById(id);
        if(staff!=null)
        return R.success(staff);
        else return R.error("查询失败");
    }

    /***
     * 修改员工数据根据id
     * @param staff
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Staff staff){
        log.info(staff.toString());
        staffService.updateById(staff);
        return R.success("员工信息修改成功");
    }
    //前台界面后端处理
    @PostMapping("/login")
    public R<Staff> login(HttpServletRequest request, @RequestBody Staff staff){

        //将页面密码进行md5加密
        String password=staff.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Staff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Staff::getUsername,staff.getUsername());
        Staff emp =staffService.getOne(lambdaQueryWrapper);

        //如果没有查询到则返回登入失败
        if(emp==null){
            return R.error("登入失败");
        }

        //密码比对
        if(emp.getPassword().equals(password)){
            return R.error("登入失败");
        }

        //登入成功
        request.getSession().setAttribute("staff",emp.getUsername());

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
        request.getSession().removeAttribute("staff");
        return R.success("退出成功");
    }



}

