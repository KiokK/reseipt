package by.kihtenkoolga.factory;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import by.kihtenkoolga.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ConsoleFactoryTest {

    @Autowired
    private ConsoleFactory consoleFactoryTest;

    //"id-(count)" или строка вида "card-(number)"
    @ParameterizedTest
    @CsvSource({
            "2-3 1-3 7-1 5-2 card-1111, 15, 1111",
            "2-3 1-3 -1 5-2 card-2222, 8, 2222",
            "2-3 1-3 1-2 7-1 5- card-7777, 10, 7777",
    })
    void checkCreateCashRegisterWithCards(String argument, int sumUniqueProductsId, long cardNumber) {
        ReceiptRequestInfo ans = consoleFactoryTest.createCashRegister(argument.split(" "));
        assertAll(
                () -> Assertions.assertThat(ans.getCard().getNumberCard()).isEqualTo(cardNumber),
                () -> Assertions.assertThat(ans.getProducts().keySet().stream().map(Product::getId)
                        .reduce(0L, Long::sum)).isEqualTo(sumUniqueProductsId)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "2-3 1-3 7-1 5-2 card-0, 9",
            "2-3 1-3 -1 5-2 card-hb, 8",
            "2-3 1-3 7-u 5- card-, 6",
            "2-3 1-3 7-u 5- card-22, 6"
    })
    void checkCreateCashRegisterWithNullCard(String argument, int sumCountProducts) {
        ReceiptRequestInfo ans = consoleFactoryTest.createCashRegister(argument.split(" "));
        assertAll(
                () -> Assertions.assertThat(ans.getCard()).isEqualTo(null),
                () -> Assertions.assertThat(ans.getProducts().values().stream().reduce(0, Integer::sum))
                        .isEqualTo(sumCountProducts)
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void checkCreateCashRegisterCorrectProductsPairs(String[] argument) {
        ReceiptRequestInfo ans = consoleFactoryTest.createCashRegister(argument);
        Assertions.assertThat(ans.getProducts().size()).isEqualTo(4);
    }

    static Stream<Arguments> argsProviderFactory() {
        return Stream.of(
                Arguments.of((Object)new String[] {"2-3", "1-3", "7-1", "5-2", "9", "a-d"}),
                Arguments.of((Object)new String[] {"4-2", "2-3", "7-1", "5-2", "-6"}));
    }
}
