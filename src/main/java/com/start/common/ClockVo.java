package com.start.common;



import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClockVo implements Serializable {
    private String name;
    private String idcard;


    private Integer id;


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
}
