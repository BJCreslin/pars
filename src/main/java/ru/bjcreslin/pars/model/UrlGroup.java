package ru.bjcreslin.pars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@NoArgsConstructor

@Table(name = "url_groupe_table")
public class UrlGroup extends BaseEntity {

    @Column(name = "groupe_name")
    String nameGroupe;

    @Column(name="url")
    String urlGroupe;

    public UrlGroup(String nameGroupe, String urlGroupe) {
        this.nameGroupe = nameGroupe;
        this.urlGroupe = urlGroupe;
    }
}
