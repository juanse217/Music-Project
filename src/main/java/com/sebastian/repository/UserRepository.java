package com.sebastian.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.sebastian.model.User;

public interface UserRepository {
    void save(User user); 
    Optional<User> findById(String id);
    Collection<User> findAll();
}
