package Awebinar;

public class OrderMovieSession {
    private MovieSession movieSession;
    private User user;

    public OrderMovieSession(MovieSession movieSession, User user) {
        this.movieSession = movieSession;
        this.user = user;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public User getUser() {
        return user;
    }
}
