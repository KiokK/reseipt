package by.kihtenkoolga.service;

import by.kihtenkoolga.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    User create(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void update(User user);

    void deleteById(Long id);

}
