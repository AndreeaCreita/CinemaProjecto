# CinemaProjecto - Backend
Developed using Java 17 and Spring Boot.

## About The Project
Cinema is an application used mainly for buying tickets to movies by movie enthusiasts. The app contains all the movies, available and unavailable, to a certain cinema and offers the posibility to buy one or more tickets for movies.
The application, also, is authentication free, which means users can get tickets without creating an account, just by using their email and name.
## Business Requirements
1. Users should be able to view the cinemas.
2. Users should be able to select a cinema.
3. Users should be able to view all the movies from a cinema.
4. Users should be able to view the movie's information after selecting it.
5. Users should be able to buy one or more tickets for a movie at time.
6. In order to find a specific movie, users should be able to filter through movies by specifying the name of the movie.
7. To find movies from a specific category, users should be able to filter by category.
8. To see which actors plays in a movie, a movie should display all of them.
9. Users should be able to pick date and time for a movie, and change it afterward.
10. Users should be able to see how many seats are left to a specific movie.
11. Tickets should display the user's first and last name, the movie, the cinema and the time and date.
12. The number of seats should be changeable.
13. Tickets should be cancellable if action is performed before the screening.

## Five Main Features

### Cinemas and Movies Exploration

1. Description: <br>
Users can explore available cinemas, view movie listings, and get information about each movie.
2. Functionality: <br>
Display a list of cinemas with basic details (name, location). <br>
Allow users to click on a cinema to view all movies available. <br>
Show movie information (title, description, genre) for each movie.

### Cinema and Movie Selection

1. Description: <br> Users can select a specific cinema, choose a movie, and view detailed information.
2. Functionality: <br>
Implement a selection process for cinemas and movies. <br>
Show detailed information about the selected movie (description, genre, actors).

### Ticket Purchase

1. Description: <br> Users can buy tickets for selected movies, choosing date and time.
2. Functionality: <br>
Allow users to pick a date and time for a specific movie. <br>
Display available seats and show the number of seats left.

### Movie Filtering

1. Description: <br> Users can find specific movies by filtering based on name and category.
2. Functionality: <br>
Provide a search option to filter movies by name. <br>
Implement category-based filters to find movies by genre.

### Ticket Details

1. Description:  <br> Users can view details of purchased tickets, including user information.
2. Functionality: <br>
Display information about purchased tickets (user's name, movie, cinema, date, time). <br>
Show the number of seats reserved for each ticket.

### Application Details

1. The application will meet the following criteria:

2. Relationships between entities of all types will be created: @OneToOne, @OneToMany, @ManyToOne, @ManyToMany.

3. All types of CRUD operations will be implemented.

4. The application will be tested using profiles and two different databases, one of which for the testing stage can use an in-memory database (H2).

5. Use unit tests/integration tests.

6. Data from forms will be validated, and exceptions will be handled.

7. Log-in features will be used. Optional aspects.

8. Options for data pagination and sorting will be used.

9. Spring Security will be included with minimal JDBC authentication.
