package com.start.service.impl;

import com.start.domain.Staff;
import com.start.mapper.StaffMapper;
import com.start.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工 服务实现类
 * </p>
 *
 * @author start
 * @since 2023-02-11
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

}
