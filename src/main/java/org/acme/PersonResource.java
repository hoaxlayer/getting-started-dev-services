package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Person;
import org.acme.repositories.PersonRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Person> search(@QueryParam("pageNumber") @DefaultValue("0") int pageNumber,
                               @QueryParam("pageSize") @DefaultValue("10") int pageSize,
                               @QueryParam("status") Status status){

        PanacheQuery<Person> entities;
        entities = personRepository.findAll();

        if (status!=null){
            entities = personRepository.find("status", status);
        } else {
            entities = personRepository.findAll();
        }

        return entities.page(Page.of(pageNumber, pageSize)).list();
    }

}
