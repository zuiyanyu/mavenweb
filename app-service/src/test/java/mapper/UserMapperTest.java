package mapper;

import com.mavenweb.domain.User;
import com.mavenweb.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class UserMapperTest {

    @Resource
    UserMapper userMapper ;

    @Test
    public void getUserByNoTest(){
        User user = userMapper.getUserByNo("123");
        System.out.println(user);
    }

    public static void main(String[] args) {

//                ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//                UserService userService = (UserServiceImpl)ctx.getBean("userService");
//                String userName = userService.getUserName("001");
//                System.out.println(userName);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper)ctx.getBean("userMapper");
        String userName = userMapper.getUserNameByNo("001");
        System.out.println(userName);
    }
}
