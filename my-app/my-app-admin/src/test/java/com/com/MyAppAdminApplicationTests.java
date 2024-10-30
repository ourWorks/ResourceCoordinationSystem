package com.com;

import com.po.CitizenPO;
import com.po.VolunteerPO;
import com.vo.CitizenVO;
import com.vo.CitizenVOMapper;
import com.vo.VolunteerVOMapper;
import com.vo.VolunteerVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
class MyAppAdminApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void citizenPo2Vo() {
        CitizenPO citizenPO =
//        new CitizenPO((long) 10,"19120331929","19120331929","苏晓龙",22,"男","894394198@qq.com894394198@qq.com");
//        CitizenPO.builder().id(10).name("苏晓龙").phone("19120331929").email("894394198@qq.com").build();
                CitizenPO.builder().id(10L).name("苏晓龙").phone("19120331929").email("894394198@qq.com").build();

        CitizenVO citizenVO =
                CitizenVOMapper.INSTANCE.po2Vo(citizenPO);

        System.out.println(citizenVO);
    }

    @Test
    void testMethod() {
        System.out.println("Hello World!");
    }

    //关于Jwt的加密测试
    @Test
    void testJwt() {

        String secret = "a1g2y47dg3dj59fjhhsd7cnewy73j";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // 生成JWT
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", "zss");
        claims.put("age", 18);

        //生成token
        String token = Jwts.builder()
                .setClaims(claims)
                .setId("666")  //登录用户的id
                .setSubject("小马")  //登录用户的名称
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))//过期时间
                .setIssuedAt(new Date(System.currentTimeMillis()))//当前时间
                .signWith(SignatureAlgorithm.HS512, secret)//头部信息 第一个参数为加密方式为哈希512  第二个参数为加的盐为secret字符串
                .compact();

        System.out.println("token令牌是：" + token);

        Claims claims1 = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Date d1 = claims1.getIssuedAt();
        Date d2 = claims1.getExpiration();
        System.out.println("username参数值：" + claims1.get("username"));
        System.out.println("登录用户的id：" + claims1.getId());
        System.out.println("登录用户的名称：" + claims1.getSubject());
        System.out.println("令牌签发时间：" + sdf.format(d1));
        System.out.println("令牌过期时间：" + sdf.format(d2));

    }

//    @Autowired
//    VolunteerMapper volunteerMapper;
//    //关于两个实体类联合的Mapper测试
    @Test
    void testVolunteerVO(){
        VolunteerPO volunteerPO = VolunteerPO.builder().id(2L).ondueState("1").build();
        CitizenPO citizenPO = CitizenPO.builder().id(3L).name("苏晓龙").build();
        VolunteerVO volunteerVO = VolunteerVOMapper.INSTANCE.po2Vo(volunteerPO, citizenPO);
        log.info("@@@@@@@@@@@@@@@@@@");
        log.info(volunteerVO.toString());
        log.info("@@@@@@@@@@@@@@@@@@");
    }


    //这样测试是不行的
//    @Autowired
//    VolunteerMapper volunteerMapper;
//
//    @Test
//    public HttpResult getOrgVolunteersPage() {
//
//        OrganizationPO organizationPO = new OrganizationPO();
//        //本组织中所有未删除的志愿者
//        Map<String, Object> paramMap = new HashMap<>();
////        paramMap.put("orgId", organizationPO.getOrgId());
//        paramMap.put("orgId", 8);
//        paramMap.put("deleteState", 0);
//
//        //设置每一页的请求，下面的1和10以后是传进来的
//        PageRequest pageRequest = new PageRequest();
//        pageRequest.setPageNum(1);
//        pageRequest.setPageSize(10);
//        pageRequest.setParams(paramMap);
//
//        //查询某一页
//        PageResult page = MybatisPageHelper.findPage(pageRequest, volunteerMapper);
//        return HttpResult.ok(page);
//    }

}
