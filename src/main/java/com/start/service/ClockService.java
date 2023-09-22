package com.start.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.ClockVo;
import com.start.domain.Clock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.start.mapper.ClockMapper;

import javax.annotation.Resource;

/**
 * <p>
 * 考勤记录 服务类
 * </p>
 *
 * @author start
 * @since 2023-02-19
 */
public interface ClockService extends IService<Clock> {
}
