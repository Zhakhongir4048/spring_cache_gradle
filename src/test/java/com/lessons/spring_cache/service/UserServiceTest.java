package com.lessons.spring_cache.service;

import com.lessons.spring_cache.AbstractTest;
import com.lessons.spring_cache.entity.User;
import org.assertj.core.api.Assertions;
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

    @Test
    void createAndRefresh() {
        User user1 = userService.createOrReturnCached(new User("Vasya", "vasya@mail.ru"));
        LOGGER.info("created user1: {}", user1);

        User user2 = userService.createOrReturnCached(new User("Vasya", "misha@mail.ru"));
        LOGGER.info("created user2: {}", user2);

        User user3 = userService.createAndRefreshCache(new User("Vasya", "kolya@mail.ru"));
        LOGGER.info("created user3: {}", user3);

        User user4 = userService.createOrReturnCached(new User("Vasya", "petya@mail.ru"));
        LOGGER.info("created user4: {}", user4);
    }

    @Test
    public void delete() {
        User user1 = userService.create(new User("Vasya", "vasya@mail.ru"));
        LOGGER.info("{}", userService.get(user1.getId()));

        User user2 = userService.create(new User("Vasya", "vasya@mail.ru"));
        LOGGER.info("{}", userService.get(user2.getId()));

        userService.delete(user1.getId());
        userService.deleteAndEvict(user2.getId());

        LOGGER.info("{}", userService.get(user1.getId()));
        Assertions.assertThatThrownBy(() -> userService.get(user2.getId()))
                .hasMessage("User not found by id 2");
    }

    private void createAndPrint(String name, String email) {
        LOGGER.info("created user: {}", userService.create(name, email));
    }

    private void getAndPrint(Long id) {
        LOGGER.info("user found {}", userService.get(id));
    }

}