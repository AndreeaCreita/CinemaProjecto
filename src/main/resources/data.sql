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

insert into cinema_movie(cinema_id, movie_id) values(1, 1);
insert into cinema_movie(cinema_id, movie_id) values(2, 1);
insert into cinema_movie(cinema_id, movie_id) values(3, 1);
insert into cinema_movie(cinema_id, movie_id) values(2, 2);

insert into movie_genre(movie_id, genre_id) values(1, 1);
insert into movie_genre(movie_id, genre_id) values(2, 1);
insert into movie_genre(movie_id, genre_id) values(3, 1);
insert into movie_genre(movie_id, genre_id) values(3, 3);
insert into movie_genre(movie_id, genre_id) values(3, 2);