package by.kihtenkoolga.builder;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;

import java.util.List;
import java.util.Map;
/**Класс чека,
 * наследуется от {@link CashRegister}, чтобы иметь все поля для построения чека,*/
public class CashRegisterDate extends CashRegister {

    public CashRegisterDate(Double price, Double discount, List<Product> onDiscount, String receipt, Shop shop,
                            Map<Product, Integer> products, String cashierNumber, DiscountCard card) {
        this.setPrice(price);
        this.setDiscount(discount);
        this.setOnDiscount(onDiscount);
        this.setReceipt(receipt);
        this.setShop(shop);
        this.setProducts(products);
        this.setCashierNumber(cashierNumber);
        this.setCard(card);
    }
}
