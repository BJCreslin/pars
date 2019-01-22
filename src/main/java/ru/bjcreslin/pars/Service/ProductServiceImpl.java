package ru.bjcreslin.pars.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.bjcreslin.pars.model.Product;
import ru.bjcreslin.pars.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void save(Product product) {
        Product product1 = productRepository.findFirstByName(product.getName());
        if (product1 != null) {
            productRepository.delete(product1);
        }
        productRepository.save(product);
    }

    @Override
    @ModelAttribute("products")
    public List<Product> getAll() {
        return productRepository.findAll();
    }


}
