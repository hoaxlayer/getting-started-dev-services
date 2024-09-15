package org.acme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.acme.Status;

import java.time.LocalDate;

@Entity
@Data
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String name;
    public LocalDate birth;
    public Status status;

}
