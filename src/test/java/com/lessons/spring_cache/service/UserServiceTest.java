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
        User user1 = userService.create(new User("Ivan", "ivan@mail.ru"));
        User user2 = userService.create(new User("Sergey", "sergey@mail.ru"));
        getAndPrint(user1.getId());
        getAndPrint(user2.getId());
        getAndPrint(user2.getId());
        getAndPrint(user2.getId());
    }

    @Test
    void create() {
        createAndPrint("Ivan", "ivan@mail.ru");
        createAndPrint("Ivan", "ivan1122@mail.ru");
        createAndPrint("Sergey", "ivan@mail.ru");
    }

    private void createAndPrint(String name, String email) {
        LOGGER.info("created user: {}", userService.create(name, email));
    }

    private void getAndPrint(Long id) {
        LOGGER.info("user found {}", userService.get(id));
    }

}