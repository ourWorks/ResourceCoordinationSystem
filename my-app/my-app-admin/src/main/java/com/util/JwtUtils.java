package com.util;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

/**
 *
 **/
@Getter
@Setter
@Component
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    //签名私钥,非常重要
    private String key = "yellowbluegreygreenredblack";
    //签名失效时间：这样写是1分钟
    private Long failureTime = (long) 1 * 60 * 1000;

    /**
     * 设置认证token
     *
     * @param id      用户登录ID
     * @param subject 用户登录名
     * @param map     其他私有数据
     * @return
     */
    public String createJwt(String id, String subject, Map<String, Object> map) {

        //1、设置失效时间啊
        long now = System.currentTimeMillis();  //毫秒
        long exp = now + failureTime;

        //2、创建JwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().
                setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                //设置签名防止篡改
                .signWith(SignatureAlgorithm.HS256, key);

        //3、根据map设置claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));

        //4、创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public Claims parseJwt(String token) {


        Claims claims;
        //如果超时就会发生ExpiredJwtException， JWT expired at 2022-03-15T11:35:06Z. Current time: 2022-03-15T12:28:26Z
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 校验令牌的时效性和正确性
     *
     * @param token
     * @return
     */

    @NotNull
    public boolean isTokenValid(String token) {
        try {
            boolean signed = Jwts.parser().setSigningKey(key).isSigned(token);
            return signed;
//            SignatureAlgorithm algorithm = SignatureAlgorithm.HS256(key);
//            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
//            DecodedJWT verify = jwtVerifier.verify(token);
        } catch (JwtException ex) {
            throw new RuntimeException("令牌失效！");
        }
    }

    public Boolean isTokenExpired(String token) {
        //不管是否过期，都返回claims对象
        Claims claims = this.parseJwt(token);
        Date expiration = claims.getExpiration();
        //和当前时间进行对比来判断是否过期
        return new Date(System.currentTimeMillis()).after(expiration);
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = parseJwt(token);
        return claims.getExpiration();
    }

}
