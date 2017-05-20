/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Shing-Cheung
 * Created: Apr 27, 2017
 */

CREATE TABLE MOVIE("ID" INTEGER not null primary key, "NAME" VARCHAR(50) not null, "YR" INTEGER, "SYNOPSIS" VARCHAR(250))
CREATE TABLE ACTOR("ID" INTEGER not null primary key, "NAME" VARCHAR(50) not null, "ABOUT" VARCHAR(250))
CREATE TABLE JOIN_MOVIE_ACTOR("MOVIE_ID" INTEGER not null, "ACTOR_ID" INTEGER not null)
ALTER TABLE JOIN_MOVIE_ACTOR ADD CONSTRAINT MOVIE_FK FOREIGN KEY ("MOVIE_ID") REFERENCES MOVIE ("ID")
ALTER TABLE JOIN_MOVIE_ACTOR ADD CONSTRAINT ACTOR_FK FOREIGN KEY ("ACTOR_ID") REFERENCES ACTOR ("ID")
