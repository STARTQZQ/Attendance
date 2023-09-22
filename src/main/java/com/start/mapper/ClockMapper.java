package com.start.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.common.ClockVo;
import com.start.common.R;
import com.start.domain.Clock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 考勤记录 Mapper 接口
 * </p>
 *
 * @author start
 * @since 2023-02-19
 */
@Mapper
public interface ClockMapper extends BaseMapper<Clock> {
    @Select("SELECT a.id a.idcard a.clock_time a.work_class b.name from staff a LEFT JOIN clock b on a.idcard=b.idcard")
    R<Page<ClockVo>> getPageVo(Page<ClockVo> iPage);
}
