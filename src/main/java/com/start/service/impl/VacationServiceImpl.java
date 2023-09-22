package com.start.service.impl;

import com.start.domain.Vacation;
import com.start.mapper.VacationMapper;
import com.start.service.VacationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 假期申请 服务实现类
 * </p>
 *
 * @author start
 * @since 2023-02-14
 */
@Service
public class VacationServiceImpl extends ServiceImpl<VacationMapper, Vacation> implements VacationService {

}
