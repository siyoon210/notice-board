package me.siyoon.noticeboard.repository;

import me.siyoon.noticeboard.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void 유저_id로_조회하기() {
        Long id = 1L;
        User user = userRepository.findUserById(id);

        Assert.assertEquals(id, user.getId());
    }

    @Test
    public void 유저_email로_조회하기() {
        String email = "admin@notice.com";
        User user = userRepository.findUserByEmail(email);

        Assert.assertEquals(email, user.getEmail());
    }

    @Test
    public void 유저_한명_삽입하기() {
        User user = User.builder().email("user@naver.com")
                .password("1234")
                .nickName("김유저").build();
        User save = userRepository.save(user);

        Assert.assertEquals(user.getEmail(), save.getEmail());
    }

    @Test
    public void 유저_한명_정보수정하기() {
        Long id = 1L;
        String newNickName = "관리자2";

        User user1 = userRepository.findUserById(id);

        user1.setNickName(newNickName);

        entityManager.flush();

        User user2 = userRepository.findUserById(id);

        Assert.assertEquals(newNickName, user2.getNickName());
    }

    @Test
    public void 유저_한명_삭제하기() {
        List<User> 삭제전List = userRepository.findAll();

        userRepository.deleteById(1L);

        List<User> 삭제후List = userRepository.findAll();

        Assert.assertEquals(삭제전List.size(), 삭제후List.size() + 1);
    }
}
