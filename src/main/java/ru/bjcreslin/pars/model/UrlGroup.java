package ru.bjcreslin.pars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "url_groupe_table")
public class UrlGroup extends BaseEntity {

    @Column(name = "groupe_name")
    String nameGroupe;

    @Column(name = "url")
    String urlGroupe;


}
