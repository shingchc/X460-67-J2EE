/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Shing-Cheung
 */
@Entity
@Table(name = "ACTOR")
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a")
    , @NamedQuery(name = "Actor.findById", query = "SELECT a FROM Actor a WHERE a.id = :id")
    , @NamedQuery(name = "Actor.findByName", query = "SELECT a FROM Actor a WHERE a.name = :name")
    , @NamedQuery(name = "Actor.findByAbout", query = "SELECT a FROM Actor a WHERE a.about = :about")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 250)
    @Column(name = "ABOUT")
    private String about;
    @JoinTable(name = "JOIN_MOVIE_ACTOR", joinColumns = {
        @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Movie> movieCollection;

    public Actor() {
    }

    public Actor(Integer id) {
        this.id = id;
    }

    public Actor(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Collection<Movie> getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(Collection<Movie> movieCollection) {
        this.movieCollection = movieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "edu.uci.javaee7.flix.entities.Actor[ id=" + id + " ]";
        return "" + id + ": " + this.name;
    }
    
}
