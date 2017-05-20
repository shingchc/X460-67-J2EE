/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.logic;

import edu.uci.javaee7.flix.entities.Movie;
import edu.uci.javaee7.flix.util.Log;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Shing-Cheung
 */
@Stateless
public class MovieService {
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init()  {
        Log.log.info("MovieService created");
//        em = Persistence.createEntityManagerFactory("flixPU").createEntityManager();
    }
    
    @PreDestroy
    public void destroy()  {
        Log.log.info("MovieService destroyed");
//        em.close();
    }
    
    public List<Movie> listMovies() {
        return em.createNamedQuery("Movie.findAll").getResultList();
    }
    
    public void addMovie(Movie movie)   {
//        em.getTransaction().begin();
        em.persist(movie);
//        em.getTransaction().commit();
    }
    
    public Movie getMovie(int id)   {
        Query query = em.createNamedQuery("Movie.findById");
        query.setParameter("id",id);
        
        return (Movie) query.getSingleResult();
    }
    
    public void deleteMovie(Movie movie)    {
        Movie m = movie;
        if (!em.contains(m)) {
            m = getMovie(movie.getId());
        }
//        em.getTransaction().begin();
        em.remove(m);
//        em.getTransaction().commit();
    }
}
