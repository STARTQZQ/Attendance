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
 * 出差申请
 * </p>
 *
 * @author start
 * @since 2023-02-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Travel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String idcard;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date applyTime;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date begin;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date end;

    /**
     * 出差地址
     */
    private String address;

    /**
     * 出差事物
     */
    private String affair;

    private String name;

    private String agree;


}
