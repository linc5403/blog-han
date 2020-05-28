package club.banyuan.blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testRedis() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("a", "1234", 2, TimeUnit.HOURS);
        logger.info(valueOperations.get("a"));
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush("b", "1");
        listOperations.leftPush("b", "2");
    }

    public void setString(String k, String v, Integer timeout) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(k, v, timeout, TimeUnit.HOURS);
    }

    public String getString(String k) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(k);
    }
}
