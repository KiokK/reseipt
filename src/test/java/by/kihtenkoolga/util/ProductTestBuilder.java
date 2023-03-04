package by.kihtenkoolga.util;

import by.kihtenkoolga.model.Product;

import java.util.List;

public class ProductTestBuilder {

    public static final String DEFAULT_NAME = "Mangogo";
    public static final Double DEFAULT_PRISE = 100.0;
    public static final byte DEFAULT_DISCOUNT_PERCENTAGE = 0;

    public static final Product APPLE = aRealProductApple().build();

    public static final Product APPLE_WITH_DISC_10 = ProductTestBuilder.aRealProductApple()
            .discountPercentage((byte) 10)
            .build();

    public static final List<Product> TESTS_PRODUCTS = List.of(
            new Product(1L, "Apple", 1.0, (byte) 0),
            new Product(2L, "Pineapple", 10.0, (byte) 0),
            new Product(3L, "Milk", 1.0, (byte) 0),
            new Product(4L, "Chocolate Alpenhold", 2.3, (byte) 10),
            new Product(5L, "Water AURA", 50.0, (byte) 20),
            new Product(6L, "Chocolate Alenka", 2.0, (byte) 0),
            new Product(7L, "Plat", 10.3, (byte) 0));

    public static Product.ProductBuilder aProduct() {
        return Product.builder().name(DEFAULT_NAME).price(DEFAULT_PRISE);
    }

    public static Product.ProductBuilder aRealProductApple() {
        return Product.builder().id(1L).name("Apple").price(1.0);
    }
}
