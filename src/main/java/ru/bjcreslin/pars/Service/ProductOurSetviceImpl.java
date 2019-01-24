package ru.bjcreslin.pars.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.pars.model.ProductOur;
import ru.bjcreslin.pars.repository.ProductOurRepository;

@Service
public class ProductOurSetviceImpl implements ProductOurService {
    @Autowired
    ProductOurRepository productOurRepository;

    @Override
    public void save(ProductOur productOur) {
        productOurRepository.save(productOur);
    }
}
