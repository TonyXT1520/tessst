package com.example.unitTest.service;

import com.example.unitTest.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestPropertySource(locations = "/application-test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testCreateUser() {
        UserEntity newUser = new UserEntity();
        newUser.setName("Mark Smith");
        newUser.setEmail("mark@example.com");

        UserEntity savedUser = userService.createUser(newUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        List<UserEntity> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testDeleteUserNotFound() {

        assertDoesNotThrow(() -> userService.deleteUser(999L));
    }

    @Test
    @Transactional
    void testCreateUserWithExistingEmail() {

        UserEntity duplicateUser = new UserEntity();
        duplicateUser.setName("Charlie");
        duplicateUser.setEmail("alice@example.com");

        assertThrows(DataIntegrityViolationException.class, () -> userService.createUser(duplicateUser));
    }

}
