package by.kihtenkoolga.builder;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;

/** Интерфейс строитель, который имеет методы для построения чека */
public interface Builder {
    /** Константа ширины (количества символов) чека */
    int LEN = 46;

    /**
     * Метод принятия данных и расчета сумм покупки и скидок
     * @param data коллекция объектов-товаров, содержащихся в бд, а так же информация о магазине,
     *             информация о карте и номер кассира
     */
    void getData(ReceiptRequestInfo data);

    /** Установка "шапки" чека с информацией о магазине */
    void setTop();

    /** Установка "середины" чека с информацией о покупках */
    void setProductsList();

    /** Установка "нижней таблицы" чека с информацией о сумме и скидках */
    void setFlow();

}
