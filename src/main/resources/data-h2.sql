insert into cinema(location, name) values('Bucaresti', 'Sanplaza');
insert into cinema(location, name) values('Bucaresti', 'Hafi');
insert into cinema(location, name) values('Bucaresti', 'Megam');

insert into genre( name) values('action');
insert into genre( name) values('fantasy');
insert into genre( name) values('horror');
insert into genre( name) values('romance');
insert into genre( name) values('comedy');
insert into genre( name) values('mafia');

insert into movie(description, title)
    values('Shrek', 'Shrekl 2');
insert into movie(description, title)
    values('Shrek', 'Shrekl32');
insert into movie(description, title)
    values('Alice in Wonderland is a very good movie', 'Alice in Wonderland');
insert into movie(description, title)
values('Movie 4', 'After');
insert into movie(description, title)
values('Movie 5', 'Hannah Montana');
insert into movie(description, title)
values('So cute', 'Baby Me');
insert into movie(description, title)
values('Shazam vs Shrek', 'Shazam Baby Me');
insert into movie(description, title)
values('A movie about bees', 'Bees');

insert into cinema_movie(cinema_id, movie_id, seats) values(1, 1, 60);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 1, 60);
insert into cinema_movie(cinema_id, movie_id, seats) values(3, 1, 80);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 2, 100);
insert into cinema_movie(cinema_id, movie_id, seats) values(3, 4, 100);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 5, 5);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 6, 10);
insert into cinema_movie(cinema_id, movie_id, seats) values(3, 7, 100);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 8, 5);
insert into cinema_movie(cinema_id, movie_id, seats) values(2, 6, 10);

insert into movie_genre(movie_id, genre_id) values(1, 1);
insert into movie_genre(movie_id, genre_id) values(2, 1);
insert into movie_genre(movie_id, genre_id) values(3, 1);
insert into movie_genre(movie_id, genre_id) values(4, 3);
insert into movie_genre(movie_id, genre_id) values(5, 2);
insert into movie_genre(movie_id, genre_id) values(6, 4);
insert into movie_genre(movie_id, genre_id) values(7, 5);
insert into movie_genre(movie_id, genre_id) values(8, 6);


insert into "user"( email, first_name, last_name) values( 'ce@email.com', 'taylor', 'swift');
insert into "user"( email, first_name, last_name) values( 'ec@email.com', 'kanye', 'west');

insert into actor(name) values('brad pitt');
insert into actor(name) values('Johnny Depp');
insert into actor(name) values('Winona Ryder');
insert into actor(name) values('Zendaya');
insert into actor(name) values('Dylan OBrien');
insert into actor(name) values('Travis Scott');
insert into actor(name) values('Ian Somehalder');
insert into actor(name) values('Paul Wesley');

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
