package by.kihtenkoolga.factory;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;

/** Интерфейс "фабричного метода" для преобразования и фильтра информации чека */
public interface Factory {
     /**
      * Метод создания коллекции информации о покупках
      * @param data данные с элементами информации о товарах и карте
      * @return коллекция объектов-товаров, содержащихся в бд, а так же информация о магазине,
      *         информация о карте и номер кассира
      */
     ReceiptRequestInfo createCashRegister(String[] data);

}
