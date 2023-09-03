package com.lessons.spring_cache.service;

import com.lessons.spring_cache.AbstractTest;
import com.lessons.spring_cache.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


class UserServiceTest extends AbstractTest {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    void get() {
        User user1 = userService.create(new User("Test1", "test1@mail.ru"));
        User user2 = userService.create(new User("Test2", "test2@mail.ru"));
        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
        getAndPrint(user2.getId());
        getAndPrint(user2.getId());
    }

    private void getAndPrint(Long id) {
        LOGGER.info("user found {}", userService.get(id));
    }

}