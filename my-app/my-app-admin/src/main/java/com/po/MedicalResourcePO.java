package com.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

/**
 *
 * @TableName medical_resource
 */
@TableName(value ="medical_resource")
@Data
//@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalResourcePO implements Serializable {
    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 口罩
     */
    @TableField(value = "mask")
    private Integer mask;

    /**
     * 绷带
     */
    @TableField(value = "bandage")
    private Integer bandage;

    /**
     * 担架
     */
    @TableField(value = "stretcher")
    private Integer stretcher;

    /**
     * 降压药
     */
    @TableField(value = "depressor")
    private Integer depressor;

    /**
     * 抗过敏药
     */
    @TableField(value = "antihistamine")
    private Integer antihistamine;

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
        MedicalResourcePO other = (MedicalResourcePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMask() == null ? other.getMask() == null : this.getMask().equals(other.getMask()))
            && (this.getBandage() == null ? other.getBandage() == null : this.getBandage().equals(other.getBandage()))
            && (this.getStretcher() == null ? other.getStretcher() == null : this.getStretcher().equals(other.getStretcher()))
            && (this.getDepressor() == null ? other.getDepressor() == null : this.getDepressor().equals(other.getDepressor()))
            && (this.getAntihistamine() == null ? other.getAntihistamine() == null : this.getAntihistamine().equals(other.getAntihistamine()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMask() == null) ? 0 : getMask().hashCode());
        result = prime * result + ((getBandage() == null) ? 0 : getBandage().hashCode());
        result = prime * result + ((getStretcher() == null) ? 0 : getStretcher().hashCode());
        result = prime * result + ((getDepressor() == null) ? 0 : getDepressor().hashCode());
        result = prime * result + ((getAntihistamine() == null) ? 0 : getAntihistamine().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mask=").append(mask);
        sb.append(", bandage=").append(bandage);
        sb.append(", stretcher=").append(stretcher);
        sb.append(", depressor=").append(depressor);
        sb.append(", antihistamine=").append(antihistamine);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
