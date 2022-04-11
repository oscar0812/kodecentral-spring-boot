package com.oscarrtorres.kodecentral.spring.boot;

import static org.assertj.core.api.Assertions.assertThat;

import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import com.oscarrtorres.kodecentral.spring.boot.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("ooo2");
        user.setEmail("ooo@gmail.com");
        user.setPassword("passpass");
        User savedUser = userService.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}
