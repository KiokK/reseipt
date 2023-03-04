package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.DiscountCardDAO;
import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.service.DiscountCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Validated
@Service
public class  DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardDAO repository;
    @Autowired
    public DiscountCardServiceImpl(DiscountCardDAO repository) {
        this.repository = repository;
    }

    @Override
    public DiscountCard create(DiscountCard discountCard) {
        discountCard.setId(null);
        return repository.save(discountCard);
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DiscountCard> findByNumberCard(Long numberCard) {
        return repository.findByNumberCard(numberCard);
    }

    @Override
    public List<DiscountCard> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(DiscountCard discountCard) {
        Objects.requireNonNull(discountCard.getId());
        repository.save(discountCard);
    }

    @Override
    public void delete(DiscountCard discountCard) {
        repository.delete(discountCard);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}