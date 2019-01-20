package ru.bjcreslin.pars.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter @ToString
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor
@Table(name = "productourtable")
public class ProductOur extends BaseEntity {
    @Basic
    @Column(name = "code")
    private Integer code;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "cost")
    private BigDecimal cost;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "producttable",
            joinColumns = @JoinColumn(name = "productourtable_id"),
            inverseJoinColumns = @JoinColumn(name = "producttable"))
    List<Product> surrogate = new ArrayList<>();

}
