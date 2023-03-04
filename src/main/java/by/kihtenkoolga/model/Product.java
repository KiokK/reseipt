package by.kihtenkoolga.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",length = 30)
    private String name;
    @Column(name = "price")
    private Double price;
    @Builder.Default
    @Column(name = "discount_percentage")
    private byte discountPercentage = 0;

    public Product(Long id, String name, Double price, byte discountPercentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
    }

    public Product(String name, Double price, byte discountPercentage) {
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
    }

    /** Если товар на скидке, то вернется true, иначе false */
    public boolean isDiscount() {
        return (discountPercentage == 0) ? false : true;
    }

}
