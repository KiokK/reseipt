package by.kihtenkoolga.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "discount_cards")
@Builder
@NoArgsConstructor
@Data
public class DiscountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "number_card", nullable = false)
    private Long numberCard;
    @Column(name = "fio", length = 30)
    private String FIO;

    public DiscountCard(@NonNull Long numberCard) {
        this.numberCard = numberCard;
    }

    public DiscountCard(Long id, @NonNull Long numberCard, String FIO) {
        this.id = id;
        this.numberCard = numberCard;
        this.FIO = FIO;
    }
}
