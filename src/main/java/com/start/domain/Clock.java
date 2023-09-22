package com.start.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 考勤记录
 * </p>
 *
 * @author start
 * @since 2023-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Clock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String idcard;

    /**
     * 考勤时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date clockTime;

    /**
     * 考勤上班还是下班
     */
    private String workClass;

    /**
     * 考勤人信息
     */
    private String name;


}
