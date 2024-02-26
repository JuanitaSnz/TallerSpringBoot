package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="idCard")
public class IdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private LocalDate expeditionDate;

    @OneToOne
    @JoinColumn(name="petId", unique = true)
    private Pet pet;

    public IdCard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(LocalDate expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
