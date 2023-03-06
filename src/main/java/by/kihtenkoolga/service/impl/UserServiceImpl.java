package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.cache.proxy.DeleteCache;
import by.kihtenkoolga.cache.proxy.GetCache;
import by.kihtenkoolga.cache.proxy.PostCache;
import by.kihtenkoolga.cache.proxy.PutCache;
import by.kihtenkoolga.dao.UserDAO;
import by.kihtenkoolga.model.User;
import by.kihtenkoolga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @PostCache
    public User create(User user) {
        Objects.requireNonNull(user);
        return userDAO.create(user);
    }

    @Override
    @GetCache
    public Optional<User> findById(Long id) {
        Objects.requireNonNull(id);
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    @PutCache
    public void update(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());
        userDAO.update(user);
    }

    @Override
    @DeleteCache
    public void deleteById(Long id) {
        Objects.requireNonNull(id);
        userDAO.deleteById(id);
    }

}
