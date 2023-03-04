package by.kihtenkoolga.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Декоратор для работы с консолью
 * Класс, реализующий прямое чтение и запись данных
 */
@Component
public class ConsoleDataSource implements DataSource {
    @Autowired
    ApplicationArguments appArgs;
    @Override
    public void writeData(String data) {
        System.out.println(data);
    }

    @Override
    public String readData() {
        String args = String.join(" ", appArgs.getSourceArgs());
        return args;
    }
}
