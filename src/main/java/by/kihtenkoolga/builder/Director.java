package by.kihtenkoolga.builder;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;

/**
 * Для регулирования работы строителей, конструирует чеки
 */
public class Director {

    public void constructCashRegister(Builder builder, ReceiptRequestInfo data) {

        builder.getData(data);
        builder.setTop();
        builder.setProductsList();
        builder.setFlow();
    };
}
