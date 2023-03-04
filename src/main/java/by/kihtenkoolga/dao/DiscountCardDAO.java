package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCardDAO extends JpaRepository<DiscountCard, Long> {
    Optional<DiscountCard> findByNumberCard(Long numberCard);
}
