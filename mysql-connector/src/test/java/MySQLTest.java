import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.onepiecejoker.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;



@SpringBootTest(classes = Application.class)
public class MySQLTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;

    @Test
    public void testQuery() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        for (Map<String, Object> map : list) {
            System.out.println("id: " + map.get("id"));
        }
        System.out.println("rows: " + list.size());
    }

    @Test
    public void testPrimary() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user where name = 'Jone'");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).get("age"), 18);
    }

    @Test
    public void testSlave() {
        List<Map<String, Object>> list = slaveJdbcTemplate.queryForList("select * from user where name = 'Tom'");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).get("age"), 28);
    }
}
