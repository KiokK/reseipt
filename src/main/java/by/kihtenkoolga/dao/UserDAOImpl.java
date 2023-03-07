package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAOImpl implements UserDAO{
    private HashMap<Long, User> userBase = new HashMap<>() {{
        put(1L, User.builder().id(1L).name("Bob").surname("Biba").phoneNumber("+375330987623").email("dsadiu@gmail.com").build());
        put(2L, new User(2L, "Viva", "Vovo", "+375330007623", "asddiu@gmail.com"));
        put(3L, new User(3L, "Chery", "Apple", "+375330900023", "diasdu@gmail.com"));
    }};

    private long nextId = 4;

    @Override
    public User create(User user) {
        Objects.requireNonNull(user);
        user.setId(nextId);
        userBase.put(nextId++, user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Objects.requireNonNull(id);
        if (userBase.containsKey(id) == false)
            return Optional.empty();
        return Optional.of(userBase.get(id));
    }

    @Override
    public List<User> findAll() {
        return userBase.values().stream().collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        Objects.requireNonNull(user);
        if (userBase.containsKey(user.getId()) == false)
            throw new RuntimeException("User with this id is not found!");
        userBase.put(user.getId(), user);
    }

    @Override
    public void deleteById(Long id) {
        userBase.remove(id);
    }
}
