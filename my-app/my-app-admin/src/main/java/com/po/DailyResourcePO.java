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
 * @TableName daily_resource
 */
@TableName(value ="daily_resource")
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyResourcePO implements Serializable {
    /**
     *
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id")
    private Long id;

    /**
     * 水（瓶）
     */
    @TableField(value = "water")
    private Integer water;

    /**
     * 充饥食品（斤）
     */
    @TableField(value = "food")
    private Integer food;

    /**
     * 帐篷（顶）
     */
    @TableField(value = "tent")
    private Integer tent;

    /**
     * 救援车辆（台）
     */
    @TableField(value = "car")
    private Integer car;

    /**
     * 救援船只（艘）
     */
    @TableField(value = "boat")
    private Integer boat;

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
        DailyResourcePO other = (DailyResourcePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWater() == null ? other.getWater() == null : this.getWater().equals(other.getWater()))
            && (this.getFood() == null ? other.getFood() == null : this.getFood().equals(other.getFood()))
            && (this.getTent() == null ? other.getTent() == null : this.getTent().equals(other.getTent()))
            && (this.getCar() == null ? other.getCar() == null : this.getCar().equals(other.getCar()))
            && (this.getBoat() == null ? other.getBoat() == null : this.getBoat().equals(other.getBoat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWater() == null) ? 0 : getWater().hashCode());
        result = prime * result + ((getFood() == null) ? 0 : getFood().hashCode());
        result = prime * result + ((getTent() == null) ? 0 : getTent().hashCode());
        result = prime * result + ((getCar() == null) ? 0 : getCar().hashCode());
        result = prime * result + ((getBoat() == null) ? 0 : getBoat().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", water=").append(water);
        sb.append(", food=").append(food);
        sb.append(", tent=").append(tent);
        sb.append(", car=").append(car);
        sb.append(", boat=").append(boat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
