package ru.bjcreslin.pars.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bjcreslin.pars.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
