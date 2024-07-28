package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.vertx.http.Compressed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Person;
import org.acme.repositories.PersonRepository;
import org.jboss.resteasy.reactive.RestQuery;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    public List<Person> list(){
        return personRepository.listAll();
    }

    public static class Parameters{
        @RestQuery
        Long id;

        @RestQuery
        String age;

        @RestQuery
        String name;

        @RestQuery
        LocalDate birth;

        @RestQuery
        Status status;

        @RestQuery
        @DefaultValue("0")
        int pageNumber;

        @RestQuery
        @DefaultValue("10")
        int pageSize;

    }

    @GET
    @Path("/{id}")
    public Person get(Long id){
        return personRepository.findById(id);
    }

    @POST
    @Transactional
    public Response create(Person person){
        personRepository.persist(person);
        return Response.created( URI.create("/persons/" + person.getId() )).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Person update(Long id, Person person){
        Person entity = personRepository.findById(id);
        if(entity==null){
            throw new NotFoundException();
        }
        entity.setId(person.getId());
        entity.setBirth(person.getBirth());
        entity.setName(person.getName());
        entity.setStatus(person.getStatus());
        return entity;
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(Long id){
        Person entity = personRepository.findById(id);
        if(entity==null){
            throw new NotFoundException();
        }
        personRepository.deleteById(id);
    }

    @GET
    @Path("/search/{name}")
    public Person search(String name){
        return personRepository.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count(){
        return personRepository.count();
    }

    @GET
    @Path("/search")
    public List<Person> search(@BeanParam Parameters parameters){

        PanacheQuery<Person> entities;

        entities = personRepository.find("status", parameters.status);


        return entities.page(Page.of(parameters.pageNumber, parameters.pageSize)).list();
    }

}
