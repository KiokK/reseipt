package by.kihtenkoolga.controller;

import by.kihtenkoolga.builder.*;
import by.kihtenkoolga.factory.HTTPFactory;
import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/check/xml")
public class ControllerWriteToXML {

    @Autowired
    private HTTPFactory httpFactory;

    @GetMapping
    public ResponseEntity<String> cashRegisterPrint(@RequestParam(required = false) List<String> itemId,
                                                  @RequestParam(required = false) String card
    ) {
        if (itemId == null)
            return ResponseEntity.badRequest()
                    .body("You have not entered the products<br/>Try: " +
                            "http://localhost:8081/check/xml?itemId=4&itemId=1&itemId=3&card=1111");

        itemId.add("card-" + card);
        String[] data = itemId.toArray(new String[0]);

        ReceiptRequestInfo cashRegisterData = httpFactory.createCashRegister(data);
        //Строитель создания чека
        CashRegisterBuilder builder = new CashRegisterBuilder();
        File xmlFile = new File("check.xml");
        try {
            new Director().constructCashRegister(builder, cashRegisterData);

            JAXBContext context = JAXBContext.newInstance(CashRegister.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(builder.getResult(), xmlFile);


        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(OK).body("File " + xmlFile.getAbsolutePath() + " was created!");
    }

}
