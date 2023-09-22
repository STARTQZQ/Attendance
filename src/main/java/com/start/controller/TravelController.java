package com.start.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.R;
import com.start.domain.Travel;
import com.start.domain.Vacation;
import com.start.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 出差申请 前端控制器
 * </p>
 *
 * @author start
 * @since 2023-02-15
 */
@Slf4j
@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={} pageSize={} name={}",page,pageSize,name);

        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Travel> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Strings.isNotEmpty(name),Travel::getName,name);

        //添加排序条件

        queryWrapper.orderByDesc(Travel::getApplyTime);

        //执行查询
        travelService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> remove(@RequestParam List<Integer> ids){
        log.info("ids:{}",ids);
        travelService.removeByIds(ids);
        return R.success("删除成功");
    }

    /**
     * 同意
     * @param ids
     * @return
     */
    @PostMapping("/agree")
    public R<String> agree(@RequestParam List<Integer> ids){
        log.info("ids:{}",ids);
        UpdateWrapper<Travel> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(Travel::getId, ids).set(Travel::getAgree,"1");
        travelService.update(updateWrapper);
        return R.success("修改成功");
    }


    //前端api

    /**
     * 前端员工出差分页数据
     * @param page
     * @param pageSize
     * @param idcard
     * @return
     */
    @GetMapping("/myPage")
    public R<Page> myPage(int page, int pageSize,String idcard){
        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Travel> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Travel::getIdcard,idcard);

        //添加排序条件

        queryWrapper.orderByDesc(Travel::getApplyTime);

        //执行查询
        travelService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 员工提交出差数据
     * @param travel
     * @return
     */
    @PostMapping
    public R<String> save(@DateTimeFormat(pattern = "yyyy-MM-dd HH") @RequestBody Travel travel){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);
        travel.setApplyTime(date);
        travelService.save(travel);
        return R.success("申请成功");
    }


}

