package com.dto;/*
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
public class VolunteerInfoDTO {
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
     * 删除状态
     */
    private Integer deleteState;
    /**
     * 经度
     */
    private Double longitude;

    /**
     * 邮箱
     */
    private Double latitude;
    /**
     * 空闲开始时间
     */
    private LocalDateTime startTime;
    /**
     * 空闲结束时间
     */
    private LocalDateTime endTime;

    /**
     * 口罩
     */
    private Integer mask;

    /**
     * 绷带
     */
    private Integer bandage;

    /**
     * 担架
     */
    private Integer stretcher;

    /**
     * 降压药
     */
    private Integer depressor;

    /**
     * 抗过敏药
     */
    private Integer antihistamine;

    /**
     * 水（瓶）
     */
    private Integer water;

    /**
     * 充饥食品（斤）
     */
    private Integer food;

    /**
     * 帐篷（顶）
     */
    private Integer tent;

    /**
     * 救援车辆（台）
     */
    private Integer car;

    /**
     * 救援船只（艘）
     */
    private Integer boat;

    public int[] getResourceArr(){
        int[] arr = {water,food,tent,car,boat,mask,bandage,stretcher,depressor,antihistamine};
        return arr;
    }
}
