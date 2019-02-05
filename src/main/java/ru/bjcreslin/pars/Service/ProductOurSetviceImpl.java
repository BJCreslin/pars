package ru.bjcreslin.pars.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.pars.model.ProductOur;
import ru.bjcreslin.pars.repository.ProductOurRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductOurSetviceImpl implements ProductOurService {
    @Autowired
    ProductOurRepository productOurRepository;

    @Override
    public void save(ProductOur productOur) {
        if ((productOur.getCode() != null) &&
                (productOurRepository.existsByCode(productOur.getCode()))) {
            ProductOur productOurFromBase =
                    productOurRepository.getOneByCode(productOur.getCode());
            if ((productOur.getName().isEmpty()) && (
                    !productOurFromBase.getName().isEmpty())
            ) {
                productOur.setName(productOurFromBase.getName());
            }

            if ((productOur.getGroupe().isEmpty()) && (
                    !productOurFromBase.getGroupe().isEmpty())
            ) {
                productOur.setGroupe(productOurFromBase.getGroupe());
            }

            if (productOur.getBase() < 1) {
                productOur.setBase(productOurFromBase.getBase());
            }
            if (productOur.getCentral() < 1) {
                productOur.setCentral(productOurFromBase.getCentral());
            }
            if (productOur.getCost().compareTo(BigDecimal.ONE) < 0) {
                productOur.setCost(productOurFromBase.getCost());
            }
            productOurRepository.delete(productOurFromBase);


        }

        productOurRepository.save(productOur);
    }

    @Override
    public void saveAll(List<ProductOur> productOurLIst) {
//todo сделать реализацию сохранения list
    }
}
