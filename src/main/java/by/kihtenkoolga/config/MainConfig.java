package by.kihtenkoolga.config;

import by.kihtenkoolga.decorator.ConsoleDataSource;
import by.kihtenkoolga.decorator.CreateOutputDecorator;
import by.kihtenkoolga.decorator.FileDataSource;
import by.kihtenkoolga.factory.ConsoleFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    /**
     * Конструктор бина чтения данных и вывода готового чека
     * с использованием {@link CreateOutputDecorator}
     * @param ds источник данных - консоль
     * @param cf фабрика формированияответа
     * @return объект <code>CreateOutputDecorator</code> с зависимостями
     */
    @Bean
    public CreateOutputDecorator createOutputConsoleDecorator(ConsoleDataSource ds, ConsoleFactory cf) {
        return new CreateOutputDecorator(ds, cf);
    }
    /**
     * Конструктор бина чтения данных и вывода готового чека
     * с использованием {@link CreateOutputDecorator}
     * @param ds источник данных - файл
     * @param cf фабрика формированияответа
     * @return объект <code>CreateOutputDecorator</code> с зависимостями
     */
    @Bean
    public CreateOutputDecorator createOutputFileDecorator(FileDataSource ds, ConsoleFactory cf) {
        return new CreateOutputDecorator(ds, cf);
    }


}