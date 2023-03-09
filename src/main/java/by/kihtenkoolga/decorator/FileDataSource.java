package by.kihtenkoolga.decorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Декоратор для работы с файлами
 * Класс, реализующий прямое чтение и запись данных при работе с файлами
 */
@Component
public class FileDataSource implements DataSource {
    private String inputName;
    private String outputName;
    private final ApplicationArguments appArgs;

    /** В конструкторе внедряется зависимость и устанавливаются имена файлов
     *  для чтения и записи, которые принимаются в аргументах при запуске приложения
     */
    @Autowired
    public FileDataSource(ApplicationArguments appArgs) {
        this.appArgs = appArgs;
        try {
            this.inputName = appArgs.getSourceArgs()[0];
            if (appArgs.getSourceArgs().length == 1)
                this.outputName = "OUTPUT"+appArgs.getSourceArgs()[0];
            else
            if (appArgs.getSourceArgs().length >= 2)
                this.outputName = appArgs.getSourceArgs()[1];
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void writeData(String data) {
        try (FileWriter writer = new FileWriter(outputName, false)) {
            writer.append(data);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String readData() {
        String data = "";
        try (FileReader reader = new FileReader(inputName)) {
            int c;
            while ((c = reader.read()) != -1)
                data += (char) c;
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return data;
    }
}
