package by.kihtenkoolga.builder;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Абстрактный класс чека с информационными полями*/
@Getter
@Setter
public abstract class CashRegister {
    Double price = 0.0;
    Double discount = 0.0;
    List<Product> onDiscount = new ArrayList<>();
    int onDiscountCount = 0;
    String receipt = "";
    Shop shop;
    Map<Product, Integer> products = new HashMap<>();
    String cashierNumber;
    DiscountCard card;

    /**Метод расчета полной цены и скидки */
    void calculatePrices() {
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            //prod_price * count
            price += product.getKey().getPrice() * product.getValue();
            if (card != null && product.getKey().isDiscount()) {
                onDiscount.add(product.getKey());
                onDiscountCount+=product.getValue();
                //discount += prod_price * (disc%)
                discount += product.getKey().getPrice() *
                        product.getKey().getDiscountPercentage() / 100.0 * product.getValue();
            }
        }
    }
    /** Метод расчета этоговой цены с учетом скидок */
    double getTotalPrise() {
        double totalPrice = price - discount;
        if (onDiscountCount >= 5) {
            for (Product pr : onDiscount)
                totalPrice -= pr.getPrice()*0.1;
        }
        return totalPrice;
    }

}
