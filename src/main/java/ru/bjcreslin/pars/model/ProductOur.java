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
@Getter
@Setter
@ToString
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

    @Column(name = "base_quantity")
    private Integer base;

    @Column(name = "central_quantity")
    private Integer central;

    @Column(name = "needed")
    private Integer needed;

    @Column(name = "groupe")
    private String groupe;

//    public ProductOur(int code, int number, String stringCellValue) {
//        this.code = code;
//        this.needed = number;
//        groupe = stringCellValue;
//        cost=BigDecimal.ZERO;
//        central=0;
//        base=0;
//    }

    public ProductOur(int code, int quantityNeeded, String name, String groupe) {

        this.code = code;
        this.needed = quantityNeeded;
        this.name = name;
        this.groupe = groupe;
        cost = BigDecimal.ZERO;
        base = 0;
        central = 0;
    }


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "producttable",
//            joinColumns = @JoinColumn(name = "productourtable_id"),
//            inverseJoinColumns = @JoinColumn(name = "producttable"))
//    List<Product> surrogate = new ArrayList<>();

}
