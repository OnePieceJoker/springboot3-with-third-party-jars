import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.onepiecejoker.Application;

@SpringBootTest(classes = Application.class)
public class SpringBootRedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;

    @Test
    public void testRedisUseSpringBootDataRedis() {
        stringRedisTemplate.opsForValue().set("foo", "bar");
        String value = stringRedisTemplate.opsForValue().get("foo");
        assertEquals(value, "bar");
    }

    @Test
    public void testRedisUseRedission() {
        redisson.getBucket("foo").set("bar");
        String value = (String) redisson.getBucket("foo").get();
        assertEquals(value, "bar");
    }
    
}
