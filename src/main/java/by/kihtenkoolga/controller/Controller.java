package by.kihtenkoolga.controller;

import by.kihtenkoolga.builder.Director;
import by.kihtenkoolga.builder.HTMLCashRegisterBuilder;
import by.kihtenkoolga.factory.HTTPFactory;
import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/check")
public class Controller {

    @Autowired
    private HTTPFactory httpFactory;
    @GetMapping
    public ResponseEntity<String> cashRegisterPrint(@RequestParam(required = false) List<String> itemId,
                                                    @RequestParam(required = false) String card
    ) {
        if (itemId == null)
            return ResponseEntity.badRequest()
                    .body("You have not entered the products<br/>Try: http://localhost:8081/check?itemId=4&itemId=1&itemId=3&card=1111");
        itemId.add("card-"+card);
        String[] data = itemId.toArray(new String[0]);

        ReceiptRequestInfo cashRegisterData = httpFactory.createCashRegister(data);
        //Строитель создания чека
        HTMLCashRegisterBuilder builder = new HTMLCashRegisterBuilder();
        String receipt = "";
        try {
            new Director().constructCashRegister(builder, cashRegisterData);
            receipt = builder.getResult().getReceipt();
        }catch (NullPointerException ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(OK).body(ex.getMessage());
        }
        return ResponseEntity.status(OK).body(receipt);
    }
}
