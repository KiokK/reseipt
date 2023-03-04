package by.kihtenkoolga.factory.model;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс информации запроса создания чека::
 * магазин, список покупок, кассир, карта
 */
@Getter
@Setter
public class ReceiptRequestInfo {
    Shop shop;
    Map<Product, Integer> products = new HashMap<>();
    String cashierNumber;
    DiscountCard card;

    public ReceiptRequestInfo(Shop shop, Map<Product, Integer> products, String cashierNumber, DiscountCard card) {
        this.shop = shop;
        this.products = products;
        this.cashierNumber = cashierNumber;
        this.card = card;
    }
}
