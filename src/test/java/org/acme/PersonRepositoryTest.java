package org.acme;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.entities.Person;
import org.acme.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class PersonRepositoryTest {

    @InjectMock
    PersonRepository personRepository;

    @Test
    public void testPersonRepository(){

        // Mocked classes always return a default value
        Assertions.assertEquals(0, personRepository.count());

        Person person = new Person();
        person.setName("MockPerson");
        person.setStatus(Status.ALIVE);

        personRepository.persist(person);

        Mockito.when(personRepository.findByName("MockPerson")).thenReturn(person);
        Assertions.assertSame(person, personRepository.findByName("MockPerson"));

        Assertions.assertSame(personRepository.findAlive().get(0).status, Status.ALIVE);

    }

}
