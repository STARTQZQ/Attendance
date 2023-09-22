package com.start.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.ClockVo;
import com.start.common.R;
import com.start.domain.Clock;
import com.start.domain.Vacation;
import com.start.filter.DateUtil;
import com.start.service.ClockService;
import com.start.service.impl.ClockServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 * 考勤记录 前端控制器
 * </p>
 *
 * @author start
 * @since 2023-02-19
 */
@Slf4j
@RestController
@RequestMapping("/clock")
public class ClockController {


    @Autowired
    private ClockServiceImpl clockService;
    /**
     * 打卡
     * @param clock
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Clock clock){
//        log.info("新增员工，员工信息{}",clock.toString());

        //调用的代码
        Date date = new Date();
        Clock emp=new Clock();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LambdaQueryWrapper<Clock> queryWrapper =   new LambdaQueryWrapper<>();
        Date start = DateUtil.strToDateLong(DateUtil.dateToStr(new Date(), Locale.CHINA) + " 00:00:00");
        Date end = DateUtil.strToDateLong(DateUtil.dateToStr(new Date(), Locale.CHINA) + " 23:59:59");
        queryWrapper.between(Clock::getClockTime, start, end);
        queryWrapper.like(Clock::getIdcard,clock.getIdcard());
        queryWrapper.like(Clock::getWorkClass,clock.getWorkClass());
        emp = clockService.getOne(queryWrapper);
//        log.info("新增打卡，信息{}",emp.toString());
        if(emp==null){
            formatter.format(date);
            clock.setClockTime(date);
            log.info("新增打卡，信息{}",clock.toString());
            clockService.save(clock);
            return R.success("打卡成功");
        }else {
            return R.error("你已打卡");
        }
    }


    /**
     * 获取打卡数据
     * @return
     */
    @GetMapping("/{idcard}")
    public R<List> getClock(@PathVariable String  idcard){
        log.info("{}",idcard);
        // 查询条件：
        LambdaQueryWrapper<Clock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Clock::getIdcard,idcard);
        // 开始查询
        List<Clock> users = clockService.list(queryWrapper);
        return R.success(users);
    }

    /**
     * 员工数据查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page={} pageSize={} name={}",page,pageSize,name);

        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Clock> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Strings.isNotEmpty(name),Clock::getName,name);

        //添加排序条件

        queryWrapper.orderByDesc(Clock::getName);

        //执行查询
        clockService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

//        Page<ClockVo> iPage =new Page<ClockVo>(page,pageSize);
//        return clockService.getPageVo(iPage);
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> remove(@RequestParam List<Integer> ids){
        log.info("ids:{}",ids);
        clockService.removeByIds(ids);
        return R.success("删除成功");
    }

    /**
     * 前端请求员工考勤的数据
     * @param page
     * @param pageSize
     * @param idcard
     * @return
     */
    @GetMapping("/myPage")
    public R<Page> myPage(int page, int pageSize,String idcard){
        //构造一个分页构造器
        Page pageInfo =new Page(page,pageSize);

        log.info(idcard);

        //构造条件构造器
        LambdaQueryWrapper<Clock> queryWrapper =new LambdaQueryWrapper();

        //添加过滤条件

        queryWrapper.like(Clock::getIdcard,idcard);

        //添加排序条件

        queryWrapper.orderByDesc(Clock::getClockTime);

        //执行查询
        clockService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);


    }

}

