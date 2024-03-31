import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.onepiecejoker.Application;

@SpringBootTest(classes = Application.class)
public class SpringBootRedisTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    Redisson redisson;

    @Value("${cache-type}")
    private String cacheType;

    private enum Enum {
        REDIS("redis"),
        GARNET("garnet");

        String type;

        Enum(String type) {
            this.type = type;
        }
    }

    @Test
    public void testCacheStoreUseSpringBootDataRedis() {
        System.out.println("---------------Test Cache Type is: " + cacheType);
        stringRedisTemplate.opsForValue().set("foo", "bar");
        String value = stringRedisTemplate.opsForValue().get("foo");
        assertEquals(value, "bar");
    }

    @Test
    public void testCacheStoreUseRedission() {
        System.out.println("---------------Test Cache Type is: " + cacheType);
        redisson.getBucket("foo").set("bar");
        String value = (String) redisson.getBucket("foo").get();
        assertEquals(value, "bar");
    }

    @Test
    public void testCacheStoreLSetUserSpringBootDataRedis() {
        System.out.println("---------------Test Cache Type is: " + cacheType);
        stringRedisTemplate.opsForList().leftPushAll("bikes:repairs", "bike:1", "bike:3");
        if (Enum.GARNET.type.equals(cacheType)) {
            // 当使用Garnet时，不支持BLPOP操作
            assertThrows(
                InvalidDataAccessApiUsageException.class, 
                () -> stringRedisTemplate.opsForList().set("bikes:repairs", 0, "bike:2")
            );
        } else if (Enum.REDIS.type.equals(cacheType)) {
            stringRedisTemplate.opsForList().set("bikes:repairs", 0, "bike:2");
            String result = stringRedisTemplate.opsForList().leftPop("bikes:repairs");
            assertEquals("bike:2", result);
        } else {
            throw new IllegalArgumentException("Cache Type is not supported: " + cacheType);
        }
    }
}
