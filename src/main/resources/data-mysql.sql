insert into cinema( location, name) values( 'Bucaresti', 'Sanplaza');
insert into cinema( location, name) values( 'Bucaresti', 'Hafi');
insert into cinema( location, name) values( 'Bucaresti', 'Megam');

insert into genre(id, name) values(1, 'action');
insert into genre(id, name) values(2, 'fantasy');
insert into genre(id, name) values(3, 'horror');
insert into genre(id, name) values(4, 'romance');
insert into genre(id, name) values(5, 'comedy');
insert into genre(id, name) values(6, 'mafia');

insert into movie(id, description, title)
    values(1, 'Shrek', 'Shrekl 2');
insert into movie(id, description, title)
    values(2, 'Shrek', 'Shrekl32');
insert into movie(id, description, title)
    values(3, 'Alice in Wonderland is a very good movie', 'Alice in Wonderland');
insert into movie(id, description, title)
values(4, 'Movie 4', 'After');
insert into movie(id, description, title)
values(5, 'Movie 5', 'Hannah Montana');
insert into movie(id, description, title)
values(6, 'So cute', 'Baby Me');
insert into movie(id, description, title)
values(7, 'Shazam vs Shrek', 'Shazam Baby Me');
insert into movie(id, description, title)
values(8, 'A movie about bees', 'Bees');

insert into cinema_movie(id, cinema_id, movie_id, seats) values(1, 1, 1, 60);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(2, 2, 1, 60);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(3, 3, 1, 80);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(4, 2, 2, 100);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(5, 3, 4, 100);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(6, 2, 5, 5);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(7, 2, 6, 10);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(8, 3, 7, 100);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(9, 2, 8, 5);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(10, 2, 6, 10);

insert into movie_genre(movie_id, genre_id) values(1, 1);
insert into movie_genre(movie_id, genre_id) values(2, 1);
insert into movie_genre(movie_id, genre_id) values(3, 1);
insert into movie_genre(movie_id, genre_id) values(4, 3);
insert into movie_genre(movie_id, genre_id) values(5, 2);
insert into movie_genre(movie_id, genre_id) values(6, 4);
insert into movie_genre(movie_id, genre_id) values(7, 5);
insert into movie_genre(movie_id, genre_id) values(8, 6);


insert into `user`( email, first_name, last_name) values( 'ce@email.com', 'taylor', 'swift');
insert into `user`( email, first_name, last_name) values( 'ec@email.com', 'kanye', 'west');

insert into actor(id, name) values(1, 'brad pitt');
insert into actor(id, name) values(2, 'Johnny Depp');
insert into actor(id, name) values(3, 'Winona Ryder');
insert into actor(id, name) values(4, 'Zendaya');
insert into actor(id, name) values(5, 'Dylan OBrien');
insert into actor(id, name) values(6, 'Travis Scott');
insert into actor(id, name) values(7, 'Ian Somehalder');
insert into actor(id, name) values(8, 'Paul Wesley');

insert into movie_actor(movie_id, actor_id) values(1, 1);
insert into movie_actor(movie_id, actor_id) values(1, 4);
insert into movie_actor(movie_id, actor_id) values(2, 3);
insert into movie_actor(movie_id, actor_id) values(3, 8);
insert into movie_actor(movie_id, actor_id) values(5, 2);
insert into movie_actor(movie_id, actor_id) values(5, 5);
insert into movie_actor(movie_id, actor_id) values(4, 6);
insert into movie_actor(movie_id, actor_id) values(5, 7);
insert into movie_actor(movie_id, actor_id) values(6, 5);
insert into movie_actor(movie_id, actor_id) values(7, 6);
insert into movie_actor(movie_id, actor_id) values(8, 7);

-- insert into adresa(id, numar, oras, strada, tara, codpostal, cinema_id) values(1,'12A', 'Bucuresti','Str. Studentilor','Romania', 1234566, 1);
