package by.kihtenkoolga.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Shop {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private List<String> cashierNumbers; //список кассиров, пока нигде не используется -> To Do: сделать

    @Builder.Default
    private List<DiscountCard> cardsNumbers = new ArrayList<>();

    public Shop(String name, String address, String phone, List<DiscountCard> cardsNumbers) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cardsNumbers = cardsNumbers;
    }

    public boolean isCardContains(DiscountCard discountCard) {
        for (DiscountCard dc : cardsNumbers) {
            if (dc.equals(discountCard))
                return true;
        }
        return false;
    }
}
