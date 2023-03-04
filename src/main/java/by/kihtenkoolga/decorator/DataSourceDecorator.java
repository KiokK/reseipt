package by.kihtenkoolga.decorator;

/**
 * Базовый декоратор.
 * Хранит ссылку на вложенный объект-компонент
 * Делегирует все свои операции вложенному объекту.
 * Дополнительное поведение будет жить в конкретных декораторах.
 */
public class DataSourceDecorator implements DataSource{
    /** Вложенный объект, которому делегируют выполнение */
    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }
    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
