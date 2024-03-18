import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onepiecejoker.Main;
import com.onepiecejoker.entity.User;
import com.onepiecejoker.mapper.UserMapper;

@SpringBootTest(classes = Main.class)
public class MybatisTest {
    
    @Autowired
    UserMapper userMapper;

    @Test
    public void testQueryAllUser() {
        List<User> users = userMapper.selectAll();
        System.out.println(users.size());
    }

    @Test
    public void testQueryUserById() {
        User user = userMapper.selectById(1L);
        assertEquals(user.getId(), 1L);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setId(6L);
        user.setAge(27);
        user.setEmail("test@163.com");
        user.setName("foo");
        userMapper.insert(user);
        User result = userMapper.selectById(6L);
        assertEquals(user.getId(), result.getId());
        userMapper.delete(3);
    }
}
