package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.ProductDAO;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Validated
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    @Override
    public Product create(Product product) {
        product.setId(null);
        return productDAO.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void update(Product product) {
        Objects.requireNonNull(product.getId());
        productDAO.save(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }
}
