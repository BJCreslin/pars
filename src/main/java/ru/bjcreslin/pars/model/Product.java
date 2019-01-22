package ru.bjcreslin.pars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Product.
 * name -name on the site
 * adres - web adress of product
 * cost - цена of product
 * adressimg - adress imagine of product
 * group- group of products
 * productourtable- table of our products
 */
@Entity
@Getter @Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor
@Table(name = "producttable")
public class Product extends BaseEntity {
    @Basic
    @Column(name = "name", insertable = true, updatable = true)
    private String name;


    @Basic
    @Column(name = "adress", insertable = true, updatable = true)
    private String adress;

    @Basic
    @Column(name = "cost", insertable = true, updatable = true)
    private BigDecimal cost;

    @Basic
    @Column(name = "costwithprior", insertable = true, updatable = true)
    private BigDecimal costWithPrior;

    @Basic
    @Column(name = "adressimg", insertable = true, updatable = true)
    private String adressIMG;

    @Basic
    @Column(name = "groupe_product", insertable = true, updatable = true)
    private String group;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "productourtable",
//            joinColumns = @JoinColumn(name = "producttable_id"),
//            inverseJoinColumns = @JoinColumn(name = "productourtable_id"))
//    private List<ProductOur> productOur = new ArrayList<>();



    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", costWithPrior=" + costWithPrior +
                '}';
    }

}
