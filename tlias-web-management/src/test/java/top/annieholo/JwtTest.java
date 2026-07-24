package top.annieholo;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.annieholo.utils.jwt.JwtUtil;

public class JwtTest {

    private static final Logger log = LoggerFactory.getLogger(JwtTest.class);

    @Test
    public void testGenerateToken(){
        String token = JwtUtil.generateToken("1");
        log.info("token: {}", token);
    }

    @Test
    public void testParseToken(){
        Claims claims = JwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzg0ODAxMjc1LCJleHAiOjE3ODQ4MDEyOTV9.f_lxGN2kmjBj-iIoOQj4J8FbXaMY5gYvZf4ziuWGVKE");
        log.info("解析token: {}", claims);
    }

    @Test
    public void testGetUserId(){
        String userId = JwtUtil.getUserId("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzg0ODAwMzc1LCJleHAiOjE3ODQ4MDM5NzV9.g9vE91WZrQrNomHNW8S_LPdhhRFz46W_KlvG3eKVkD8");
        log.info("解析token获取id: {}", userId);
    }
}
