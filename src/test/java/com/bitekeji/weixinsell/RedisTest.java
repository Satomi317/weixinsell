package com.bitekeji.weixinsell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author yuisama
 * @date 2018/8/27 15:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    public void saveTest() {
        redisTemplate.opsForValue()
                .set("test","yuisama",7200, TimeUnit.SECONDS);

    }
}
