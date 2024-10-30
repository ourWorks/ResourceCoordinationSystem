package com.vo;

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
public class CallHelpVO{
    /**
     * 操作号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cid;

    /**
     * 求助时间
     */
    private LocalDateTime callTime;

    /**
     * 求助IP
     */
    private String ip;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 人数
     */
    private Integer peopleNum;

    /**
     * 最迟时间
     */
    private LocalDateTime deadline;

    /**
     * 求助状态
     */
    private String state;

    /**
     * 备注
     */
    private String comment;

    /**
     * 日用资源需求
     */
    private String dailyResource;

    /**
     * 医疗资源需求
     */
    private String medicalResource;

}
