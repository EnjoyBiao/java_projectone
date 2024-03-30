package com.chenbiao.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey="chenbiao";
    private static Long   expire= 43200000L;// 12个小时
    /**
     * 生成令牌
     */
     public static  String generationJwt(Map<String,Object> claims){
         // 有bug 解决不了
//         String jwt = Jwts.builder()
//                 .signWith(SignatureAlgorithm.HS512,signKey)
//                 .addClaims(claims)
//                 .setExpiration(new Date(System.currentTimeMillis()+expire))
//                 .compact();

         return "chen";
     }

    /***
     * 解析令牌
     */
    public static Claims parseJwt(String jwt){
       Claims claims = Jwts.parser()
               .setSigningKey(signKey)
               .parseClaimsJwt(jwt)
               .getBody();
       return  claims;
    }
}
