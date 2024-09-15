package org.acme.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Person;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
}
