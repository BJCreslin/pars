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
    @Column(name = "name")
    private String name;


    @Basic
    @Column(name = "adress")
    private String adress;

    @Basic
    @Column(name = "cost")
    private BigDecimal cost;

    @Basic
    @Column(name = "costwithprior")
    private BigDecimal costWithPrior;

    @Basic
    @Column(name = "adressimg")
    private String adressIMG;

    @Basic
    @Column(name = "group")
    private String group;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "productourtable",
            joinColumns = @JoinColumn(name = "producttable_id"),
            inverseJoinColumns = @JoinColumn(name = "productourtable_id"))
    private List<ProductOur> productOur = new ArrayList<>();



    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", costWithPrior=" + costWithPrior +
                '}';
    }

}
