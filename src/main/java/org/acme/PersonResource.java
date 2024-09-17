package org.acme;

import io.quarkus.vertx.http.Compressed;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.persistence.LockModeType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.repositories.PersonRepository;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{id}")
    public Uni<Response> get(@PathParam("id") String id) {
        return personRepository.findById(Long.valueOf(id), LockModeType.READ)
                .onItem()
                .transform(person -> Response.ok().entity(person).build())
                .onFailure()
                .transform(failure -> new ServerErrorException(Response.serverError().build(), failure));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Compressed
    public Uni<Response> list(){
        return personRepository.listAll()
                .onItem()
                .transform(personList -> Response.ok(personList).build());
    }

    /*
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
    */
}
