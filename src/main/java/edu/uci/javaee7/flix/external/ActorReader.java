/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uci.javaee7.flix.external;

import edu.uci.javaee7.flix.entities.Actor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Shing-Cheung
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ActorReader implements MessageBodyReader<List<Actor>>{

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return new LinkedList<Actor>().getClass().isAssignableFrom(type);
    }

    @Override
    public List<Actor> readFrom(Class<List<Actor>> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        List<Actor> actors = new LinkedList();
        Actor actor = null;
        boolean movieId = false;
        
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next())  {
                case START_OBJECT:
                    actor = new Actor();
                    movieId = false;
                    break;
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key)    {
                        case "id":
                            int id = parser.getInt();
                            if (!movieId)   {
                                actor.setId(id);
                            }
                            break;
                        case "name":
                            String name = parser.getString();
                            if (!movieId)   {
                                actor.setName(name);
                            }
                            break;
                        case "about":
                            String about = parser.getString();
                            actor.setAbout(about);
                            break;
                        case "movieId":
                            movieId = true;
                            int mId = parser.getInt();
                            break;
                        default:
                            break;
                    }
                    break;
                case END_OBJECT:
                    actors.add(actor);
                    movieId = false;
                    break;
                default:
                    break;
            }
        }
        return actors;
    }
    
}
