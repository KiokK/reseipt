package by.kihtenkoolga.service;

import by.kihtenkoolga.model.DiscountCard;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {
    DiscountCard create(@NotNull DiscountCard bankAccount);
    Optional<DiscountCard> findById(@NotNull Long id);
    Optional<DiscountCard> findByNumberCard(@NotNull Long numberCard);
    List<DiscountCard> findAll();

    void update(@NotNull DiscountCard discountCard);

    void delete(@NotNull DiscountCard discountCard);

    void deleteById(@NotNull Long id);
}
