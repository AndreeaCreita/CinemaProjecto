insert into cinemas(id, location, name) values(1, 'Bucaresti', 'Sanplaza');
insert into cinemas(id, location, name) values(2, 'Bucaresti', 'Hafi');
insert into cinemas(id, location, name) values(3, 'Bucaresti', 'Megam');

insert into genres(id, name) values(1, 'action');
insert into genres(id, name) values(2, 'fantasy');
insert into genres(id, name) values(3, 'horror');

insert into movies(id, description, title, cinema_id)
    values(1, 'Shrek', 'Shrekl 2', 1);
insert into movies(id, description, title, cinema_id)
    values(2, 'Shrek', 'Shrekl32', 1);
insert into movies(id, description, title, cinema_id)
    values(3, 'Shrek', 'Shrekl 12312', 1);

insert into movie_genre(movie_id, genre_id) values(1, 1);
insert into movie_genre(movie_id, genre_id) values(2, 1);
insert into movie_genre(movie_id, genre_id) values(3, 1);
insert into movie_genre(movie_id, genre_id) values(3, 3);
insert into movie_genre(movie_id, genre_id) values(3, 2);