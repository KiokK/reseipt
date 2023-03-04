package by.kihtenkoolga.decorator;

import by.kihtenkoolga.builder.CashRegisterBuilder;
import by.kihtenkoolga.builder.Director;
import by.kihtenkoolga.factory.ConsoleFactory;
import by.kihtenkoolga.factory.model.ReceiptRequestInfo;

/**
 * Класс чтения данных и вывода готового чека
 *
 * Декоратор для работы с данными источника.
 * В {@link by.kihtenkoolga.config.MainConfig} прописаны beans для вариантов создания декоратора.
 */
public class CreateOutputDecorator extends DataSourceDecorator {
    private ConsoleFactory consoleFactory;

    public CreateOutputDecorator(DataSource source, ConsoleFactory consoleFactory) {
        super(source);
        this.consoleFactory = consoleFactory;
    }

    @Override
    public void writeData(String data) {
        super.writeData(create(data));
    }

    @Override
    public String readData() {
        return super.readData();
    }

    /**
     * Метод создаёт готовый чек используя фабрику {@link ConsoleFactory}
     * @param data информация о покупке в формате "id-count ... card-number"
     * @return готовый чек
     */
    public String create(String data) {
        ReceiptRequestInfo сashRegisterInformation = consoleFactory.createCashRegister(data.split("\\s+"));
        //Строитель создания чека
        CashRegisterBuilder builder = new CashRegisterBuilder();
        new Director().constructCashRegister(builder, сashRegisterInformation);
        return builder.getResult().getReceipt();
    }


}
