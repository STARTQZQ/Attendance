package com.start.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.ClockVo;
import com.start.common.R;
import com.start.domain.Clock;
import com.start.mapper.ClockMapper;
import com.start.service.ClockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 考勤记录 服务实现类
 * </p>
 *
 * @author start
 * @since 2023-02-19
 */
@Service
public class ClockServiceImpl extends ServiceImpl<ClockMapper, Clock> implements ClockService {
    @Resource
    private ClockMapper clockMapper;

    public R<Page<ClockVo>> getPageVo(Page<ClockVo> iPage) {
        return clockMapper.getPageVo(iPage);
    }
}
