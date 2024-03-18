import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.onepiecejoker.Main;
import com.onepiecejoker.entity.User;
import com.onepiecejoker.mapper.UserMapper;

@SpringBootTest(classes = Main.class)
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("-----SelectAll method test-------");
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
    }

    @Test
    public void testSelectByQuery() {
        LambdaQueryWrapper<User> queryWrapper = User.gw();
        queryWrapper.eq(User::getName, "Tom");
        List<User> userList = userMapper.selectList(queryWrapper);
        Assert.isTrue("Tom".equals(userList.get(0).getName()), null);
    }
    
}
