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
 * @TableName login_log
 */
@TableName(value ="login_log")
@Data
public class LoginLogPO implements Serializable {
    /**
     * 登录号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "logId")
    private Long logId;

    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "id")
    private Long id;

    /**
     * 登录时间
     */
    @TableField(value = "loginTime")
    private LocalDateTime loginTime;

    /**
     * 网络IP地址
     */
    @TableField(value = "ip")
    private String ip;

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
        LoginLogPO other = (LoginLogPO) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getDeleteState() == null ? other.getDeleteState() == null : this.getDeleteState().equals(other.getDeleteState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getDeleteState() == null) ? 0 : getDeleteState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", id=").append(id);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", ip=").append(ip);
        sb.append(", deleteState=").append(deleteState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
