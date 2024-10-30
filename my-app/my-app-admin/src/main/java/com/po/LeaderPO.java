package com.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @TableName leader
 */
@TableName(value ="leader")
@Data
@Builder
public class LeaderPO implements Serializable {
    /**
     * 领导Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "leaderId", type = IdType.AUTO)
    private Long leaderId;

    /**
     * 组织号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "orgId")
    private Long orgId;

    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "id")
    private Long id;

    /**
     * 删除状态
     */
    @TableField(value = "deleteState")
    private Integer deleteState;

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
        LeaderPO other = (LeaderPO) that;
        return (this.getLeaderId() == null ? other.getLeaderId() == null : this.getLeaderId().equals(other.getLeaderId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeleteState() == null ? other.getDeleteState() == null : this.getDeleteState().equals(other.getDeleteState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLeaderId() == null) ? 0 : getLeaderId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeleteState() == null) ? 0 : getDeleteState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", leaderId=").append(leaderId);
        sb.append(", orgId=").append(orgId);
        sb.append(", id=").append(id);
        sb.append(", deleteState=").append(deleteState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
