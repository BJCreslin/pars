package ru.bjcreslin.pars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bjcreslin.pars.model.ProductOur;


@Repository
public interface ProductOurRepository extends JpaRepository<ProductOur, Long> {
}
