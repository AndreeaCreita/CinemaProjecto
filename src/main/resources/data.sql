insert into cinema(id, location, name) values(1, 'Bucaresti', 'Sanplaza');
insert into cinema(id, location, name) values(2, 'Bucaresti', 'Hafi');
insert into cinema(id, location, name) values(3, 'Bucaresti', 'Megam');

insert into genre(id, name) values(1, 'action');
insert into genre(id, name) values(2, 'fantasy');
insert into genre(id, name) values(3, 'horror');

insert into movie(id, description, title)
    values(1, 'Shrek', 'Shrekl 2');
insert into movie(id, description, title)
    values(2, 'Shrek', 'Shrekl32');
insert into movie(id, description, title)
    values(3, 'Shrek', 'Shrekl 12312');

insert into cinema_movie(id, cinema_id, movie_id, seats) values(1, 1, 1, 60);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(2, 2, 1, 60);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(3, 3, 1, 80);
insert into cinema_movie(id, cinema_id, movie_id, seats) values(4, 2, 2, 100);

insert into movie_genre(movie_id, genre_id) values(1, 1);
insert into movie_genre(movie_id, genre_id) values(2, 1);
insert into movie_genre(movie_id, genre_id) values(3, 1);
insert into movie_genre(movie_id, genre_id) values(3, 3);
insert into movie_genre(movie_id, genre_id) values(3, 2);

insert into user(id, email, first_name, last_name) values(1, 'ce@email.com', 'taylor', 'swift');
insert into user(id, email, first_name, last_name) values(2, 'ec@email.com', 'kanye', 'west');

insert into actor(id, name) values(1, 'brad pitt');
insert into actor(id, name) values(2, 'Johnny Depp');
insert into actor(id, name) values(3, 'Winona Ryder');
insert into actor(id, name) values(4, 'Zendaya');

insert into movie_actor(movie_id, actor_id) values(1, 1);
insert into movie_actor(movie_id, actor_id) values(1, 4);
insert into movie_actor(movie_id, actor_id) values(2, 3);

-- insert into adresa(id, numar, oras, strada, tara, codpostal, cinema_id) values(1,'12A', 'Bucuresti','Str. Studentilor','Romania', 1234566, 1);
