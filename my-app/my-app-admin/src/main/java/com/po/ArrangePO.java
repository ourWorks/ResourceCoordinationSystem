package com.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 *
 * @TableName arrange
 */
@TableName(value ="arrange")
@Data
public class ArrangePO implements Serializable {
    /**
     * 组织号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "orgId")
    private Long orgId;

    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "id")
    private Long id;

    /**
     * 排班策略
     */
    @TableField(value = "strategy")
    private String strategy;

    /**
     * 操作时间
     */
    @TableField(value = "arrangeTime")
    private LocalDateTime arrangeTime;

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
        ArrangePO other = (ArrangePO) that;
        return (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStrategy() == null ? other.getStrategy() == null : this.getStrategy().equals(other.getStrategy()))
            && (this.getArrangeTime() == null ? other.getArrangeTime() == null : this.getArrangeTime().equals(other.getArrangeTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStrategy() == null) ? 0 : getStrategy().hashCode());
        result = prime * result + ((getArrangeTime() == null) ? 0 : getArrangeTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orgId=").append(orgId);
        sb.append(", id=").append(id);
        sb.append(", strategy=").append(strategy);
        sb.append(", arrangeTime=").append(arrangeTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
