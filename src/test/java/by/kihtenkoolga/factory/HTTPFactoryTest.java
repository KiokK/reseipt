package by.kihtenkoolga.factory;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class HTTPFactoryTest {

    @Autowired
    private HTTPFactory httpFactory;

    //каждый элемент - "id" товара или строка вида "'card'-number"
    @ParameterizedTest
    @CsvSource({
            "2 1 7 5 card-0, 15",
            "2 1 5 3 4 card-h3b, 15",
            "2 7 1 1 a card-, 11"
    })
    void createCashRegisterWithNullCardAndSumAllProductId(String argument, long sumIdProducts) {
        ReceiptRequestInfo ans = httpFactory.createCashRegister(argument.split(" "));
        assertAll(
                () -> Assertions.assertThat(ans.getCard()).isEqualTo(null),
                () -> Assertions.assertThat(ans.getProducts().entrySet().stream()
                                .map(pair -> pair.getKey().getId() * pair.getValue())
                                .reduce(0L, Long::sum))
                        .isEqualTo(sumIdProducts)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "2 1 7 5 card-1111, 15, 1111",
            "2 1 5 a 4 card-4444, 12, 4444",
            "2 7 1 1 2 card-1234, 13, 1234"
    })
    void createCashRegisterWithCardAndSumProductId(String argument, long sumIdProducts, long cardNumber) {
        ReceiptRequestInfo ans = httpFactory.createCashRegister(argument.split(" "));
        assertAll(
                () -> Assertions.assertThat(ans.getCard().getNumberCard()).isEqualTo(cardNumber),
                () -> Assertions.assertThat(ans.getProducts().entrySet().stream()
                                .map(pair -> pair.getKey().getId() * pair.getValue())
                                .reduce(0L, Long::sum))
                        .isEqualTo(sumIdProducts)
        );
    }
}