package top.annieholo.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil{

    // JWT 签名密钥，HS256 至少需要 32 字节
    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            "12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8)
    );
    // expiration

    private static final long EXPIRATION_TIME = 20 * 1000; // 20s
    // private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000; // 12小时

    /**
     * 生成token
     * @param userId
     * @return
     */
    public static String generateToken(String userId) throws RuntimeException{
        return Jwts.builder()
                // 设置令牌主体，一般放用户 ID
                .subject(userId)
                // 设置签发时间
                .issuedAt(new Date())
                // 设置过期时间，这里是 1 小时后
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 使用密钥对令牌签名，防止令牌被篡改
                .signWith(KEY)
                // 生成最终的 JWT 字符串
                .compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith(KEY) // 设置验签密钥，必须和生成令牌时使用的密钥一致
                .build()// 构建 JWT 解析器
                // 解析并验证令牌：
                // 1. 验证签名是否正确
                // 2. 验证令牌是否过期
                // 3. 解析出 payload 中的 claims
                .parseSignedClaims(token)
                // 获取令牌中的业务数据
                .getPayload();
    }

    /**
     * 获取userId（subject）
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        Claims claims = parseToken(token);

        // subject 就是生成令牌时放入的 userId
        return claims.getSubject();
    }
}
