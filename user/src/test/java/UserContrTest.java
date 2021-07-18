import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codeman.blog0703.UserAPP;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/18 10:05
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserAPP.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserContrTest {

    @Resource
    private IUserService userService;

    @Test
    public void createUser() {
        User user = userService.getById(1);
        user.setUserId(null);
        user.setName("xiaohong");
        user.setNickname("小红");
        user.setPhoneNum("12312312333");
        boolean save = userService.save(user);
    }

    @Test
    public void selectDeleteUser() {
        User user = userService.getById(5);
        System.out.println(user);
    }

    @Test
    public void selectDeleteUserQW() {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getName, "xiaoming"));
        System.out.println(user);
    }
}
