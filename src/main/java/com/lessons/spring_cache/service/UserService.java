package com.lessons.spring_cache.service;

import com.lessons.spring_cache.entity.User;

public interface UserService {

    User create(User user);

    User create(String name, String email);

    User get(Long id);

    User createOrReturnCached(User user);

    User createAndRefreshCache(User user);

    void delete(Long id);

    void deleteAndEvict(Long id);

}