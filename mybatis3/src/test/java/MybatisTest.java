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
        User user = userMapper.selectById(1);
        assertEquals(user.getId(), 1);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setId(3);
        userMapper.insert(user);
        User result = userMapper.selectById(3);
        assertEquals(user.getId(), result.getId());
        userMapper.delete(3);
    }
}
