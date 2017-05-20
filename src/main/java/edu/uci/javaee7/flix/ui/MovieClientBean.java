/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.ui;

import edu.uci.javaee7.flix.entities.Movie;
import edu.uci.javaee7.flix.logic.MovieService;
import edu.uci.javaee7.flix.util.Log;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Shing-Cheung
 */
@Named
@RequestScoped
public class MovieClientBean implements Serializable{
    
    @EJB
    MovieService movieService;
    
    @Inject
    MovieBackingBean bean;
    
    private Movie movies[] = null;
    private Movie movie = null;
    
    public Movie getMovie()    {
        
        if (movie == null)  {
            
            try {
                if (bean.getId() != 0)  {
                    movie = movieService.getMovie(bean.getId());
                }
            } catch (EJBException ex)   {
                
            }
        }
        
        return movie;
    }
    
    public Movie[] getMovies()   {
        
        if (movies == null) {
            List<Movie> list = movieService.listMovies();
            movies = list.toArray(new Movie[0]);
        }
        
        return movies;
    }
    
    public void addMovie()  {
        
        Movie m = new Movie();
        m.setId(bean.getId());
        m.setYr(bean.getYear());
        m.setName(bean.getName());
        m.setSynopsis(bean.getSynopsis());
        movieService.addMovie(m);
        bean.clear();
    }
    
    public void deleteMovie()   {
        
        movieService.deleteMovie(getMovie());
        movies = null;
        bean.clear();
    }
    
    @PostConstruct
    public void init()  {
        Log.log.info("***** Movie Client Bean Initialized");
    }
    
    @PreDestroy
    public void destroy()   {
        Log.log.info("***** Movie Client Bean destroyed");
    }
}
