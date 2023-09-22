package com.start.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.R;
import com.start.domain.Vacation;
import com.start.service.VacationService;
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
 * 假期申请 前端控制器
 * </p>
 *
 * @author start
 * @since 2023-02-14
 */
@Slf4j
@RestController
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={} pageSize={} name={}",page,pageSize,name);

        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Vacation> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Strings.isNotEmpty(name),Vacation::getName,name);

        //添加排序条件

        queryWrapper.orderByDesc(Vacation::getApplyTime);

        //执行查询
        vacationService.page(pageInfo,queryWrapper);
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
        vacationService.removeByIds(ids);
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
        UpdateWrapper<Vacation> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().in(Vacation::getId, ids).set(Vacation::getAgree,"1");
        vacationService.update(updateWrapper);
        return R.success("修改成功");
    }


    //前端界面

    @GetMapping("/myPage")
    public R<Page> myPage(int page, int pageSize,String idcard){
        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Vacation> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Vacation::getIdcard,idcard);

        //添加排序条件

        queryWrapper.orderByDesc(Vacation::getApplyTime);

        //执行查询
        vacationService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 员工提交请假数据
     * @param vacation
     * @return
     */
    @PostMapping
    public R<String> save(@DateTimeFormat(pattern = "yyyy-MM-dd HH") @RequestBody Vacation vacation){
        log.info("新增信息{}",vacation.toString());

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.format(date);
        vacation.setApplyTime(date);
        vacationService.save(vacation);
        return R.success("申请成功");
    }

}

