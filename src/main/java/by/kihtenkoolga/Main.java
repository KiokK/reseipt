package by.kihtenkoolga;

import by.kihtenkoolga.decorator.ConsoleDataSource;
import by.kihtenkoolga.decorator.CreateOutputDecorator;
import by.kihtenkoolga.decorator.DataSourceDecorator;
import by.kihtenkoolga.decorator.FileDataSource;
import by.kihtenkoolga.factory.ConsoleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Внедрение зависимости для обработки чтения и записи из консоли
     * {@link by.kihtenkoolga.config.MainConfig#createOutputConsoleDecorator(ConsoleDataSource, ConsoleFactory)}
     */
    @Autowired
    private CreateOutputDecorator createOutputConsoleDecorator;
    /**
     * Внедрение зависимости для обработки чтения и записи из файла
     * {@link by.kihtenkoolga.config.MainConfig#createOutputFileDecorator(FileDataSource, ConsoleFactory)}
     */
    @Autowired
    private CreateOutputDecorator createOutputFileDecorator;

    @Override
    public void run(ApplicationArguments appArgs) {
        try {
            File f = new File(appArgs.getSourceArgs()[0]);
            if (f.exists()) {
                DataSourceDecorator fileDataSource = createOutputFileDecorator;
                //обработка данных файла
                String receiptDataFromFile = fileDataSource.readData();
                fileDataSource.writeData(receiptDataFromFile);
            } else {
                DataSourceDecorator cons = createOutputConsoleDecorator;
                //обработка данных аргументов консоли
                String receiptDataFromArgs = cons.readData();
                cons.writeData(receiptDataFromArgs);
            }
        }catch (Exception e) {}
    }

}