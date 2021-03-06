package com.adhocsensei.ahsuserservice.dao;

import com.adhocsensei.ahsuserservice.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repo;

    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() {
        repo.deleteAll();

        user1 = new User();
        user1.setAuthority("Sensei");
        user1.setFirstName("Nikita");
        user1.setLastName("Pokamestov");
        user1.setEmail("nikita@email.com");
        user1.setPassword("password");
        user1.setBio("A bio");
        user1.setInstructor(true);
//        user1.setSenseisCreatedCourses(null);
        user1.setStudentsRegisteredCourses(null);

        repo.save(user1);

        user2 = new User();
        user2.setAuthority("Sensei");
        user2.setFirstName("Yashsa");
        user2.setLastName("Asgharpour");
        user2.setEmail("yasha@email.com");
        user2.setPassword("password");
        user2.setBio("A bio");
        user2.setInstructor(true);
//        user2.setSenseisCreatedCourses(null);
        user2.setStudentsRegisteredCourses(null);

        repo.save(user2);

        user3 = new User();
        user3.setAuthority("Sensei");
        user3.setFirstName("Dale");
        user3.setLastName("Kittendorf");
        user3.setEmail("dale@email.com");
        user3.setPassword("password");
        user3.setBio("A bio");
        user3.setInstructor(true);
//        user3.setSenseisCreatedCourses(null);
        user3.setStudentsRegisteredCourses(null);

        repo.save(user3);
    }

    @Test
    public void shouldAddAndGetUserFromDatabase() {

        User fromRepo = repo.findById(user1.getId()).get();
//        fromRepo.setSenseisCreatedCourses(null);
        fromRepo.setStudentsRegisteredCourses(null);
        assertEquals(user1, fromRepo);
    }

    @Test
    public void shouldUpdateUserInDatabase() {
        user1.setBio("A new bio");
        repo.save(user1);

        User fromRepo = repo.findById(user1.getId()).get();
//        fromRepo.setSenseisCreatedCourses(null);
        fromRepo.setStudentsRegisteredCourses(null);
        assertEquals(user1, fromRepo);
    }

    @Test
    public void shouldDeleteUserFromDatabase() {
        repo.deleteById(user1.getId());

        Optional<User> fromRepo = repo.findById(user1.getId());

        assertFalse(fromRepo.isPresent());
    }

    @Test
    public void shouldFindUserByEmail() {
        User result1 = repo.findByEmail("nikita@email.com");
//        result1.setSenseisCreatedCourses(null);
        result1.setStudentsRegisteredCourses(null);
        User result2 = repo.findByEmail("yasha@email.com");
//        result2.setSenseisCreatedCourses(null);
        result2.setStudentsRegisteredCourses(null);
        User result3 = repo.findByEmail("dale@email.com");
//        result3.setSenseisCreatedCourses(null);
        result3.setStudentsRegisteredCourses(null);

        assertEquals(user1,result1);
        assertEquals(user2,result2);
        assertEquals(user3,result3);

    }

}