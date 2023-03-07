package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ProductDAOTest {
    @Autowired
    private ProductDAO productDAO;

    @Nested
    class FindByName {
        private Product expectedProduct = Product.builder()
                .name("Plate")
                .id(7L)
                .discountPercentage((byte) 0)
                .price(10.3)
                .build();

        @Test
        void checkFindByNameReturnNotEmpty() {
            Optional<Product> product = productDAO.findByName("Plate");
            Assertions.assertThat(product)
                    .isNotEmpty();
        }

        @Test
        void checkFindByNameReturnExpectedObject() {
            Optional<Product> product = productDAO.findByName("Plate");
            Assertions.assertThat(product.get())
                    .isEqualTo(expectedProduct);
        }
    }
}