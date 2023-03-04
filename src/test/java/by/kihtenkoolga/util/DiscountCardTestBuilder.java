package by.kihtenkoolga.util;

import by.kihtenkoolga.model.DiscountCard;

public class DiscountCardTestBuilder {

    public static final String DEFAULT_FIO = "Ivanov I.I.";
    public static final Long DEFAULT_NUMBER = 11110L;

    public static DiscountCard aRealDiscountCard() {
        return DiscountCard.builder()
                .id(1L)
                .FIO("Viktorov I.A")
                .numberCard(1234L)
                .build();
    }

}
