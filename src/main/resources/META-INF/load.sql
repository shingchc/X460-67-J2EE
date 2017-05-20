/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Shing-Cheung
 * Created: Apr 27, 2017
 */

INSERT INTO MOVIE("ID", "NAME", "YR", "SYNOPSIS") VALUES (1, 'The Terminator', 1984, 'A cybernetic robot wreaks havoc')
INSERT INTO MOVIE("ID", "NAME", "YR", "SYNOPSIS") VALUES (2, 'Serenity', 2005, 'A crew of the ship Serenity discover a governmental secret') 
INSERT INTO MOVIE("ID", "NAME", "YR", "SYNOPSIS") VALUES (3, 'Clear and Present Danger', 1994, 'CIA Analyst discovers an illegal drug war') 
INSERT INTO MOVIE("ID", "NAME", "YR", "SYNOPSIS") VALUES (4, 'Life of Brian', 1979, 'Baby born next to Jesus mistaken for a messiah') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(8, 'Arnold Schwarzenegger', 'Born in in Austria in 1947. Was a professional body builder and California Governator') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(9, 'Michael Biehn', 'Born in 1956 in Nebraska. Won drama scholarship to University of Arizona') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(10, 'Linda Hamilton', 'Born in in 1956 in Maryland. Studied for two years at Washington College') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(7, 'Summer Glau', 'Born in 1981 in San Antonio, TX. Dallas Cowboys fan.') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(6, 'Gina Torres', 'Born in 1969 in New York City. She is a gifted soprano and has trained in opera and jazz.') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(5, 'Nathon Fillion', 'Born in 1971 in Edmonton, Alberta, Canada, Eh. Known for playing highly likeable characters.') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(4, 'Willem Dafoe', 'Born in 1955 in Appleton, Wisconsin. A prolific actor that has performed in over 80 films.') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(3, 'Harrison Ford', 'Born in Chicago, Illinois, in 1942. Dropped out of Ripon College, Wisconsin.') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(1, 'Graham Chapman', 'Born in 1941, died 1989. Studied Medicine. Known for comedic role in Monty Pythons Flying Circus') 
INSERT INTO ACTOR("ID", "NAME", "ABOUT") VALUES(2, 'John Cleese', 'Born in 1939. Tall at a very early eage. Known for comedic role in Monty Pythons Flying Circus') 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(1, 8) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(1, 9) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(1, 10) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(2, 6) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(2, 5) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(3, 3) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(3, 4) 
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(4, 1)
INSERT INTO JOIN_MOVIE_ACTOR("MOVIE_ID", "ACTOR_ID") VALUES(4, 2)