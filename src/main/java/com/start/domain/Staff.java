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
 * 员工
 * </p>
 *
 * @author start
 * @since 2023-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
    private String idcard;

    /**
     * 性别
     */
    private String sex;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门
     */
    private String section;

    /**
     * 电话
     */
    private String phone;

    /**
     * 入职时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")		//转化时间戳
    private Date entryTime;




}
