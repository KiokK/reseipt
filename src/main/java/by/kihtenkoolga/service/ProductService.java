package by.kihtenkoolga.service;

import by.kihtenkoolga.model.Product;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(@NotNull Product product);
    Optional<Product> findById(@NotNull Long id);
    List<Product> findAll();

    void update(@NotNull Product product);

    void delete(@NotNull Product product);

    void deleteById(@NotNull Long id);
}
