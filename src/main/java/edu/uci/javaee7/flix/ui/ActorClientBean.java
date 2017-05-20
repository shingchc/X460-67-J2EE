/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.ui;

import edu.uci.javaee7.flix.entities.Actor;
import edu.uci.javaee7.flix.entities.Movie;
import edu.uci.javaee7.flix.external.ActorReader;
import edu.uci.javaee7.flix.logic.MovieService;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;


/**
 *
 * @author Shing-Cheung
 */
@Model
public class ActorClientBean {

    @PersistenceContext
    EntityManager em;
    
    @EJB
    MovieService movieService;
    
    @Inject
    ActorBackingBean actorBean;
    
    private Actor actorArray[] = null;
    
    public Actor[] getActors()  {
        if (actorArray == null) {
            List<Actor> actors = new LinkedList();          

            Client client = ClientBuilder.newClient();
            client.register(ActorReader.class);

            WebTarget target = client.target("http://localhost:8080/flix/webresources/actorservice");

            actors = (List<Actor>) target.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new LinkedList<Actor>().getClass());
            
            actorArray = actors.toArray(new Actor[0]);
        }
        return actorArray;
        
    }
    
    @Transactional
    public void addActor()  {
        Movie movie = movieService.getMovie(actorBean.getMovieId());
        
        Actor actor = new Actor();
        actor.setId(actorBean.getId());
        actor.setName(actorBean.getName());
        actor.setAbout(actorBean.getAbout());
        
        List<Movie> list = new LinkedList();
        list.add(movie);
        actor.setMovieCollection(list);
        
        em.persist(actor);
    }
}
