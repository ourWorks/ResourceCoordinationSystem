package com.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName volunteer
 */
@TableName(value ="volunteer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerPO implements Serializable {
    /**
     * 志愿号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "volunteerId", type = IdType.AUTO)
    private Long volunteerId;

    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "id")
    private Long id;


    /**
     * 组织号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "orgId")
    private Long orgId;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 邮箱
     */
    @TableField(value = "latitude")
    private Double latitude;


    /**
     * 值班状态
     */
    @TableField(value = "ondueState")
    private String ondueState;

    /**
     * 删除状态
     */
    @TableField(value = "deleteState")
    private Integer deleteState;

    /**
     * 空闲开始时间
     */
    @TableField(value = "startTime")
    private LocalDateTime startTime;

    /**
     * 空闲结束时间
     */
    @TableField(value = "endTime")
    private LocalDateTime endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VolunteerPO other = (VolunteerPO) that;
        return (this.getVolunteerId() == null ? other.getVolunteerId() == null : this.getVolunteerId().equals(other.getVolunteerId()))
            && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getVolunteerId() == null ? other.getVolunteerId() == null : this.getVolunteerId().equals(other.getVolunteerId()))
            && (this.getOndueState() == null ? other.getOndueState() == null : this.getOndueState().equals(other.getOndueState()))
            && (this.getDeleteState() == null ? other.getDeleteState() == null : this.getDeleteState().equals(other.getDeleteState()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVolunteerId() == null) ? 0 : getVolunteerId().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getVolunteerId() == null) ? 0 : getVolunteerId().hashCode());
        result = prime * result + ((getOndueState() == null) ? 0 : getOndueState().hashCode());
        result = prime * result + ((getDeleteState() == null) ? 0 : getDeleteState().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", volunteerId=").append(volunteerId);
        sb.append(", id=").append(id);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", orgId=").append(orgId);
        sb.append(", volunteerId=").append(volunteerId);
        sb.append(", ondueState=").append(ondueState);
        sb.append(", deleteState=").append(deleteState);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
