package com.start.service.impl;

import com.start.domain.Travel;
import com.start.mapper.TravelMapper;
import com.start.service.TravelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出差申请 服务实现类
 * </p>
 *
 * @author start
 * @since 2023-02-15
 */
@Service
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements TravelService {

}
