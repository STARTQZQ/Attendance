package com.start.mapper;

import com.start.domain.Vacation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 假期申请 Mapper 接口
 * </p>
 *
 * @author start
 * @since 2023-02-14
 */
@Mapper
public interface VacationMapper extends BaseMapper<Vacation> {

}
