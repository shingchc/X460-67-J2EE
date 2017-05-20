/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.logic;

import edu.uci.javaee7.flix.entities.Actor;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Shing-Cheung
 */
public class ActorBean {
    private HashMap<Integer, Actor> actors = new HashMap();
    
    public void addActor(Actor actor)   {
        actors.put(actor.getId(), actor);
    }
    
    public void deleteActor(Actor actor)    {
        actors.remove(actor.getId());
    }
    
    public Actor getActor(int id)   {
        return actors.get(id);
    }
    
    public Collection getActors()   {
        return actors.values();
    }
    
    public void clearActors()   {
        actors.clear();
    }
}
