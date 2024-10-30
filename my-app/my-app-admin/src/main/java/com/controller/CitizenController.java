package com.controller;


import com.alibaba.fastjson.util.IOUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.service.RedisService;
import com.common.http.HttpResult;
import com.po.CitizenPO;
import com.po.DailyResourcePO;
import com.po.MedicalResourcePO;
import com.service.CitizenService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.service.DailyResourceService;
import com.service.MedicalResourceService;
import com.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private MedicalResourceService medicalResourceService;
    @Autowired
    private DailyResourceService dailyResourceService;

    @Autowired
    private JwtUtils jwtUtils;

    //用来存取token的redisService
    @Autowired
    private RedisService redisService;

    /**
     * 一、注册 如果市民身份证号、手机号或者邮箱已经存在，那么就提示已经存在的字段
     */
    @PostMapping("/register")
    public HttpResult reg(@RequestBody CitizenPO citizenPO,HttpServletRequest request) {
        if (citizenService.registered(citizenPO) != null) {
            return HttpResult.error("该市民的手机号、邮箱或者身份证已被注册！");
        }
        /**
         * 如果不存在，就加进数据库，注册成功
         * */
        else {
            citizenService.save(citizenPO);
            //同时要创建他所携带的资源数据

            QueryWrapper<CitizenPO> queryWrapper = new QueryWrapper<>();
            //曾经管理过该组织，只是后来退出了
            queryWrapper.eq("phone", citizenPO.getPhone());
            CitizenPO hasIdCitizen = citizenService.getOne(queryWrapper);

            //数据库表已经给其它选项设定默认值为0
            MedicalResourcePO medicalResourcePO = MedicalResourcePO.builder().id(hasIdCitizen.getId()).build();
            medicalResourceService.save(medicalResourcePO);
            DailyResourcePO dailyResourcePO = DailyResourcePO.builder().id(hasIdCitizen.getId()).build();
            dailyResourceService.save(dailyResourcePO);
            return HttpResult.ok();
        }
    }

    @Autowired
    private Producer producer;

    @GetMapping("kaptcha.jpg")
    public void kaptcha(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store,no-cache");
        response.setContentType("image/jepg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存验证码到Session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        //有些版本是IOUtils.closeQuietly(out);
        IOUtils.close(out);
    }

    @GetMapping("verify")
    public HttpResult verify(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException {
        //从Session获取保存的验证码
        String storeText = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        return HttpResult.ok(storeText);
    }

    /**
     * 二、登录 如果市民身份证号、手机号或者邮箱已经符合，并且密码符合，那么就登录成功
     */
    @PostMapping("/login")
    public HttpResult login(@RequestBody CitizenPO citizenPO, HttpServletRequest request) throws IOException {
        System.out.println("###" + citizenPO.toString());
        CitizenPO one_citizen = citizenService.verify(citizenPO);
        if (one_citizen == null) {
            return HttpResult.error("手机号，邮箱，身份证号或者密码输入错误");
        } else {
            //其他数据以map集合存放在token中
            Map<String, Object> dataMap = new HashMap<>();
            //生成token并存入数据返回
            String token = jwtUtils.createJwt(one_citizen.getId().toString(), one_citizen.getName(), dataMap);
            //系统登录认证,生成token
            System.out.println("测试Id字符串化："+one_citizen.getId().toString());
            redisService.set(one_citizen.getId().toString(),token);
            dataMap.put("token",token);
            dataMap.put("id",one_citizen.getId());
            dataMap.put("name",one_citizen.getName());
            return HttpResult.ok(dataMap);
        }
    }

    //三、在志愿者模块查询自己公开的医疗资源
    @PostMapping("/getMedicalResource")
    public HttpResult getMedicalResource(HttpServletRequest request) throws IOException {
        MedicalResourcePO medicalResourcePO = medicalResourceService.getById(request.getHeader("id"));
        return HttpResult.ok(medicalResourcePO);
    }

    //四、在志愿者模块查询自己公开的日用资源
    @PostMapping("/getDailyResource")
    public HttpResult getDailyResource(HttpServletRequest request) throws IOException {
        DailyResourcePO dailyResourcePO = dailyResourceService.getById(request.getHeader("id"));
        return HttpResult.ok(dailyResourcePO);
    }

    //五、在志愿者模块查询更新市民的资源【千万要记住不要少加@RequestBody注解，否则是接收不到的】
    @PostMapping("/updateMedicalResource")
    public HttpResult updateMedicalResource(@RequestBody MedicalResourcePO medicalResourcePO) throws IOException {
        if(medicalResourceService.updateById(medicalResourcePO)){
            return HttpResult.ok(medicalResourcePO);
        }else{
            return HttpResult.error("修改医疗资源失败~");
        }
    }

    //六、在志愿者模块查询自己公开的日用资源
    @PostMapping("/updateDailyResource")
    public HttpResult updateDailyResource(@RequestBody DailyResourcePO dailyResourcePO) throws IOException {
        if(dailyResourceService.updateById(dailyResourcePO)){
            return HttpResult.ok(dailyResourcePO);
        }else{
            return HttpResult.error("修改日用资源失败~");
        }
    }

    @PostMapping("/logout")
    public HttpResult logout(HttpServletRequest request) throws IOException {
        System.out.println("做一些使token失效的工作");
        redisService.del("1");
        return HttpResult.ok(new CitizenPO());
    }

}
