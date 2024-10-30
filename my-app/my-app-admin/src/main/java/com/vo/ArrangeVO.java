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
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
//从实体类中拷贝时候的public class CallHelpVO implements Serializable 后面的实现记得删除，否则之后的转换会失败
public class ArrangeVO{
    /**
     * 操作号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cid;

    /**
     * 值班状态
     */
    private String ondueState;

    /**
     * 志愿者姓名
     */
    private String volunteerName;

    /**
     * 志愿者手机号
     */
    private String volunteerPhone;

    /**
     * 市民姓名
     */
    private String citizenName;

    /**
     * 市民手机号
     */
    private String citizenPhone;

    /**
     * 安排时间
     */
    private LocalDateTime time;

    /**
     * 救援状态
     */
    private String state;

}
