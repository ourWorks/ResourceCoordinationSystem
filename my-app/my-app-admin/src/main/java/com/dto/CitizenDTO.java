package com.dto;/*
 *@program:my-app
 *@author: 苏晓龙
 */
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CitizenDTO {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码，就是为了用来接受参数，不出现400错误
     */
    private String password2;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 头像
     */
    private byte[] img;
}
