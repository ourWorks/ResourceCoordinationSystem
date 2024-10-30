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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrgResourceVO {
    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

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
}
