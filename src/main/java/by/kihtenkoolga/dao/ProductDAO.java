package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Qualifier("products")
@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
