package com.xx.travels.test;

import com.xx.travels.TravelsApplication;
import com.xx.travels.entity.User;
import com.xx.travels.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("xiaofeng");
        user.setPassword("123123123");
        user.setEmail("123@qq.com");
        userService.register(user);
    }

}
