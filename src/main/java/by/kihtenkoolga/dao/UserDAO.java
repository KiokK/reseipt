package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User create(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void update(User user);

    void deleteById(Long id);

}
