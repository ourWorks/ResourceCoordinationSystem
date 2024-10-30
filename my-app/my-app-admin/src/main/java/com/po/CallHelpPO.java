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
 * @TableName call_help
 */
@TableName(value = "call_help")
@Data
public class CallHelpPO implements Serializable {
    /**
     * 操作号
     */
    @JsonSerialize(using = ToStringSerializer.class)  //为了解决前端JS 的number类数据超出限制（不够存储Long，就转化成字符串）
    @TableId(value = "cid")
    private Long cid;

    /**
     * 市民号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "id")
    private Long id;

    /**
     * 志愿者号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "volunteerId")
    private Long volunteerId;

    /**
     * 求助时间
     */
    @TableField(value = "callTime")
    private LocalDateTime callTime;

    /**
     * 最迟时间
     */
    @TableField(value = "deadline")
    private LocalDateTime deadline;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 求助IP
     */
    @TableField(value = "ip")
    private String ip;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 人数
     */
    @TableField(value = "peopleNum")
    private Integer peopleNum;

    /**
     * 求助状态
     */
    @TableField(value = "state")
    private String state;

    /**
     * 日用资源需求
     */
    @TableField(value = "dailyResource")
    private String dailyResource;

    /**
     * 医疗资源需求
     */
    @TableField(value = "medicalResource")
    private String medicalResource;

    /**
     * 备注
     */
    @TableField(value = "comment")
    private String comment;

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
        CallHelpPO other = (CallHelpPO) that;
        return (this.getCid() == null ? other.getCid() == null : this.getCid().equals(other.getCid()))
                && (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCallTime() == null ? other.getCallTime() == null : this.getCallTime().equals(other.getCallTime()))
                && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
                && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
                && (this.getPeopleNum() == null ? other.getPeopleNum() == null : this.getPeopleNum().equals(other.getPeopleNum()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getDailyResource() == null ? other.getDailyResource() == null : this.getDailyResource().equals(other.getDailyResource()))
                && (this.getMedicalResource() == null ? other.getMedicalResource() == null : this.getMedicalResource().equals(other.getMedicalResource()))
                && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
                && (this.getDeleteState() == null ? other.getDeleteState() == null : this.getDeleteState().equals(other.getDeleteState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCid() == null) ? 0 : getCid().hashCode());
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCallTime() == null) ? 0 : getCallTime().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPeopleNum() == null) ? 0 : getPeopleNum().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDailyResource() == null) ? 0 : getDailyResource().hashCode());
        result = prime * result + ((getMedicalResource() == null) ? 0 : getMedicalResource().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getDeleteState() == null) ? 0 : getDeleteState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", id=").append(id);
        sb.append(", callTime=").append(callTime);
        sb.append(", deadline=").append(deadline);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", ip=").append(ip);
        sb.append(", phone=").append(phone);
        sb.append(", peopleNum=").append(peopleNum);
        sb.append(", state=").append(state);
        sb.append(", dailyResource=").append(dailyResource);
        sb.append(", medicalResource=").append(medicalResource);
        sb.append(", comment=").append(comment);
        sb.append(", deleteState=").append(deleteState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    //去到VolunteerInfoDTO对比吧
    public int[] getResourceArr() {
        //        String resourceStr = "饮用水:water,充饥食品:food,帐篷:tent,车位:car,船位:boat,口罩:mask,绷带:bandage,担架:stretcher,降压药:depressor,抗过敏药:antihistamine";
        //数组自动初始化默认为0
        int[] arr = new int[10];
        String[] tempArr = (dailyResource + "," + medicalResource).split(",");
        for (int i = 0; i < tempArr.length; i++) {
            switch (tempArr[i]) {
                case ("饮用水"):
                    arr[0] = this.peopleNum;
                    break;
                case ("充饥食品"):
                    arr[1] = this.peopleNum;
                    break;
                case ("帐篷"):
                    arr[2] = this.peopleNum;
                    break;
                case ("车位"):
                    arr[3] = this.peopleNum;
                    break;
                case ("船位"):
                    arr[4] = this.peopleNum;
                    break;
                case ("口罩"):
                    arr[5] = this.peopleNum;
                    break;
                case ("担架"):
                    arr[6] = this.peopleNum;
                    break;
                case ("绷带"):
                    arr[7] = this.peopleNum;
                    break;
                case ("降压药"):
                    arr[8] = this.peopleNum;
                    break;
                case ("抗过敏药"):
                    arr[9] = this.peopleNum;
                    break;
//                default:
//                    arr[0]=0;
            }
        }
        return arr;
    }
}
