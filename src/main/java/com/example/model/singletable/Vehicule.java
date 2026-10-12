package com.example.model.singletable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_vehicules_single")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "categorie_vehicule",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
public abstract class Vehicule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "constructeur", nullable = false)
    private String constructeur;

    public Vehicule() {
    }

    public Vehicule(String constructeur) {
        this.constructeur = constructeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }
}