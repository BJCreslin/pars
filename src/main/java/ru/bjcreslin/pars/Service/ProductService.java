package ru.bjcreslin.pars.Service;


import ru.bjcreslin.pars.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id);

    void deleteById(Long id);

    void delete(Product product);

    void save(Product product);

    List<Product> getAll();
}
