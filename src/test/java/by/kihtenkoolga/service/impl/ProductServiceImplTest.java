package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.ProductDAO;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.service.ProductService;
import by.kihtenkoolga.util.ProductTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceImplTest {
    @MockBean
    private ProductDAO productDAO;
    @Autowired
    private ProductService productService;

    @Nested
    class Create {

        private Product expectedProduct = ProductTestBuilder.aProduct().id(8L).build();
        @AfterEach
        void tearDown() {
            productService.delete(expectedProduct);
        }

        @Test
        void checkCreateNewProductWithoutId() {
            Product newProduct = ProductTestBuilder.aProduct().build();

            Mockito.doReturn(expectedProduct)
                    .when(productDAO)
                    .save(newProduct);

            newProduct = productService.create(newProduct);
            Assertions.assertThat(newProduct)
                    .isEqualTo(expectedProduct);
        }
    }

    @Nested
    class FindById {

        @Test
        void checkFindByIdReturnId1() {
            Product expectedProduct = ProductTestBuilder.APPLE;
            final Long REAL_ID = 1L;
            Mockito.doReturn(Optional.of(expectedProduct))
                    .when(productDAO)
                    .findById(REAL_ID);
            Optional<Product> product = productService.findById(REAL_ID);
            Assertions.assertThat(product.get().getId())
                    .isEqualTo(REAL_ID);
        }

        @Test
        void checkFindByIdReturnEmpty() {
            final long NO_REAL_ID = 912481902L;
            Mockito.doReturn(Optional.empty())
                    .when(productDAO)
                    .findById(NO_REAL_ID);
            Optional<Product> product = productService.findById(NO_REAL_ID);
            Assertions.assertThat(product)
                    .isEmpty();
        }
    }

    @Nested
    class FindAll {

        @Test
        void checkFindAllReturn7() {
            List<Product> products = ProductTestBuilder.TESTS_PRODUCTS;
            Mockito.doReturn(products)
                    .when(productDAO)
                    .findAll();
            List<Product> findDC = productService.findAll();
            Assertions.assertThat(findDC).hasSize(7);
        }

        @Test
        void checkFindAllReturnNo0() {
            List<Product> products = ProductTestBuilder.TESTS_PRODUCTS;
            Mockito.doReturn(products)
                    .when(productDAO)
                    .findAll();
            List<Product> findDC = productService.findAll();
            Assertions.assertThat(findDC)
                    .hasSizeGreaterThan(0);
        }
    }

    @Nested
    class Update {

        @AfterEach
        void tearDown() {
            productService.update(ProductTestBuilder.APPLE);
        }

        @Test
        void checkUpdateReturnProductWithSameId() {
            Product appleWithDisc10 = ProductTestBuilder.APPLE_WITH_DISC_10;
            long expectedId = ProductTestBuilder.APPLE.getId();
            productService.update(appleWithDisc10);

            Assertions.assertThat(appleWithDisc10.getId())
                    .isEqualTo(expectedId);
        }
        @Test
        void checkUpdateReturnProductWithNewDiscount() {
            Product appleWithDisc10 = ProductTestBuilder.APPLE_WITH_DISC_10;
            byte expectedDiscount = 10;
            productService.update(appleWithDisc10);

            Assertions.assertThat(appleWithDisc10.getDiscountPercentage())
                    .isEqualTo(expectedDiscount);
        }
    }

    @Nested
    class Delete {

        @Test
        void checkDeleteRealObject() {
            Product apple = ProductTestBuilder.APPLE;
            productService.delete(apple);
            Mockito.verify(productDAO).delete(apple);
        }
        @Test
        void checkDeleteAfterCreate() {
            Product dellProduct = productService.create(ProductTestBuilder.aProduct().build());

            productService.delete(dellProduct);
            Mockito.verify(productDAO).delete(dellProduct);
        }
    }

    @Nested
    class DeleteById {
        @Test
        void checkDeleteByRealId() {
            Long REAL_ID = 1L;
            Mockito.doNothing()
                    .when(productDAO).deleteById(REAL_ID);
            productService.deleteById(REAL_ID);
            Mockito.verify(productDAO).deleteById(REAL_ID);
        }
        @Test
        void checkDeleteByNoRealId() {
            Long NO_REAL_ID = 19809L;
            Mockito.doNothing()
                    .when(productDAO).deleteById(NO_REAL_ID);
            productService.deleteById(NO_REAL_ID);
            Mockito.verify(productDAO).deleteById(NO_REAL_ID);
        }
    }
}
