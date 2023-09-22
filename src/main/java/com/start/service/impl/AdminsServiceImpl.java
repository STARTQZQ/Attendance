package com.start.service.impl;

import com.start.domain.Admins;
import com.start.mapper.AdminsMapper;
import com.start.service.AdminsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author start
 * @since 2023-02-10
 */
@Service
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements AdminsService {

}
