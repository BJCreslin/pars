package ru.bjcreslin.pars.Service;

import ru.bjcreslin.pars.model.ProductOur;

import java.util.List;

public interface ProductOurService {
    void save(ProductOur productOur);
    void saveAll(List<ProductOur> productOurLIst);
}
