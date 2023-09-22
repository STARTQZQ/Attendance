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

/**
 * <p>
 * 假期申请
 * </p>
 *
 * @author start
 * @since 2023-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Vacation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String  idcard;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date applyTime;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date begin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date end;

    private String workClass;

    /**
     * 申请原因
     */
    private String reason;

    /**
     * 批准人
     */
    private String agree;


}
