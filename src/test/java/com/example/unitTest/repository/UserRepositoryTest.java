package com.example.unitTest.repository;

import com.example.unitTest.entity.UserEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
@Sql(scripts = "/test_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail(){
        UserEntity userEntity = userRepository.findByEmail("alice@example.com");
        assertNotNull(userEntity);
        assertEquals("Alice", userEntity.getName());
    }

    @Test
    void testFindAllUser(){
        List<UserEntity> userEntities = userRepository.findAll();
        assertEquals(3, userEntities.size());
    }

}
