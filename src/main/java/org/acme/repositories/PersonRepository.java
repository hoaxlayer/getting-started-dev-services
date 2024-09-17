package org.acme.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Person;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.exceptions.FaultToleranceException;

@ApplicationScoped
@CircuitBreaker(requestVolumeThreshold = 100, failureRatio = 0.2, delay = 5)
public class PersonRepository implements PanacheRepository<Person> {

    public void testFaultTolerance() {
        throw new FaultToleranceException();
    }

}
