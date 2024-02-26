package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pets")
public class Pet  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDay;
    private String race;
    private String sex;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name="fk_pets_to_owner"))
    @JsonIgnore
    private Owner owner;

    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private IdCard idCard;

    @ManyToMany
    @JoinTable(
            name="pets_vaccines",
            joinColumns = @JoinColumn(name="idPet"),
            inverseJoinColumns = @JoinColumn(name = "idVaccine")
    )
    public List<Vaccine> vaccines;

    public Pet() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }
}
