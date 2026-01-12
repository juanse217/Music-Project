package com.sebastian.repository.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sebastian.model.User;
import com.sebastian.repository.UserRepository;

public class UserRepositoryMemoryImpl implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null"); //We can validate basic correctness of the data passed by the Service. 
        }
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String id) {
        if(id == null || id.isBlank()) return Optional.empty(); //We use this for robustness. It makes harder to break our system. 
        return Optional.ofNullable(users.get(id)); // we use this one because the method can throw a
                                                   // NullPointerException
    }

    @Override
    public Collection<User> findAll() {
        return Collections.unmodifiableCollection(users.values());
    }

}
