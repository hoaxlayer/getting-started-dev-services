package org.acme.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.acme.Status;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Person{

    @Id @GeneratedValue
    private Long id;

    public String name;
    public LocalDate birth;
    public Status status;

}
