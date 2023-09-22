package com.start.mapper;

import com.start.domain.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工 Mapper 接口
 * </p>
 *
 * @author start
 * @since 2023-02-11
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

}
