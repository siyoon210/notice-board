package me.siyoon.noticeboard.service;

import me.siyoon.noticeboard.domain.User;
import me.siyoon.noticeboard.dto.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void 유저_한건_이메일로_조회하기() {
        String email = "admin@notice.com";
        User user = userService.getUser(email);

        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void 유저_한건_이메일로_조회하기_이메일NULL인경우() {
        String email = null;

        User user = null;
        try {
            user = userService.getUser(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Assert.assertNull(user);
    }


    @Test
    public void 유저_한건_인덱스ID로_조회하기() {
        Long id = 1L;
        User user = userService.getUser(id);

        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void 회원가입으로_유저_한건_추가하기() {
        String email = "newUser@naver.com";

        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setNickName("신유저");
        userForm.setPassword("12345");

        User user1 = userService.signUp(userForm);
        User user2 = userService.getUser(email);

        Assert.assertEquals(user1.getEmail(), user2.getEmail());
    }
}
