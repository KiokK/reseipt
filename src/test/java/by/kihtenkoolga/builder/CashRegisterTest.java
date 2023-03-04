package by.kihtenkoolga.builder;

import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.util.DiscountCardTestBuilder;
import by.kihtenkoolga.util.ShopTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;

class CashRegisterTest {

    private CashRegisterDate cashRegisterDate =
            new CashRegisterDate(0.0,0.0, new ArrayList<>(), "",
                    ShopTestBuilder.defaultShop(),
                    ShopTestBuilder.ALL_PRODUCTS,
                    "12312",
                    DiscountCardTestBuilder.aRealDiscountCard()
            );


    @Test
    void checkCalculatePricesShouldReturnCorrectPriceAndOnDiscount() {
        Double expectedPrice = ShopTestBuilder.ALL_PRODUCTS.entrySet().stream()
                .map(pair -> pair.getKey().getPrice() * pair.getValue())
                .reduce(0.0, Double::sum);
        List<Product> expectedOnDiscount = ShopTestBuilder.ALL_PRODUCTS.entrySet().stream()
                .filter(pair -> pair.getKey().isDiscount())
                .map(pair -> pair.getKey())
                .collect(Collectors.toList());

        cashRegisterDate.calculatePrices();

        assertAll(
                () -> Assertions.assertThat(cashRegisterDate.price).isEqualTo(expectedPrice),
                () -> Assertions.assertThat(cashRegisterDate.onDiscount).isEqualTo(expectedOnDiscount)
        );
    }

    @Test
    void getTotalPrise() {
        double expectedPrice = 293.42;
        cashRegisterDate.calculatePrices();
        double totalPrise = cashRegisterDate.getTotalPrise();
        Assertions.assertThat(totalPrise).isEqualTo(expectedPrice);
    }
}