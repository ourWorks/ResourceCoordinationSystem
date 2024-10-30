package com.vo;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VolunteerVO {
    /**
     * 志愿号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long volunteerId;
    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 组织号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 邮箱
     */
    private Double latitude;
    /**
     * 值班状态
     */
    private String ondueState;
    /**
     * 空闲开始时间
     */
    private LocalDateTime startTime;
    /**
     * 空闲结束时间
     */
    private LocalDateTime endTime;
}
