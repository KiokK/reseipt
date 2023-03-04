package by.kihtenkoolga.util;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;

import java.util.List;
import java.util.Map;

public class ShopTestBuilder {

    public static final Map<Product, Integer> ALL_PRODUCTS = Map.of(
            new Product(1L,"Apple", 1.0, (byte) 0), 1,
            new Product(2L,"Pineapple", 10.0, (byte) 0), 2,
            new Product(3L,"Milk", 1.0, (byte) 0), 3,
            new Product(4L,"Chocolate Alpenhold", 2.3, (byte) 10), 5,
            new Product(5L,"Water AURA", 50.0, (byte) 20), 6,
            new Product(6L,"Chocolate Alenka", 2.0, (byte) 0), 7,
            new Product(7L,"Plat", 10.3, (byte) 0), 1
    );

    public static Shop defaultShop() {
        return new Shop("GROSHIK", "Minsk, Surganova, 56", "+375660001233",
                listOfCards());
    }
    public static List<DiscountCard> listOfCards() {
        return List.of(
                new DiscountCard(1L,1234L,"Viktorov I.A"),
                new DiscountCard(2L,1111L,"Kihtenko O.Y."),
                new DiscountCard(3L,2222L,"Bondor I.K."),
                new DiscountCard(4L,3333L,"Manin I.A."),
                new DiscountCard(5L,4444L,"Vikont I.A."),
                new DiscountCard(6L,5555L,"Molotkov I.A."),
                new DiscountCard(7L,6666L,"Druid I.A."),
                new DiscountCard(8L,7777L,"Vedmak I.A."),
                new DiscountCard(9L,8888L,"Krasni I.A."),
                new DiscountCard(10L,9999L,"Kokolov I.A.")
        );
    }
}
