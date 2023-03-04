package by.kihtenkoolga.builder;

/**Класс строитель чека для HTML страницы,
 * наследуется от {@link CashRegisterBuilder}, так как логика работы у них одинаковая,
 * реализует интерфейс {@link Builder}*/
public class HTMLCashRegisterBuilder extends CashRegisterBuilder implements Builder {

    public CashRegister getResult() {
        return new CashRegisterDate(price, discount, onDiscount,
                "<pre>"+receipt+"</pre>", shop, products, cashierNumber, card);
    }
}
