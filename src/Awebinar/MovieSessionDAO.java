package Awebinar;

import java.util.ArrayList;
import java.util.List;

public class MovieSessionDAO {

    private static ArrayList<MovieSession> movieSessions = new ArrayList<>();

    private OrderMovieSessionDAO orderMovieSessionDAO = new OrderMovieSessionDAO();

    public MovieSession findByMovieNameAndAddress(String movieName, String address){
        for (MovieSession movieSession : movieSessions){
            if (movieSession.getAddress().equals(address) && movieSession.getMovieName().equals(movieName)){
                return movieSession;
            }
        }
        return null;
    }

    public int findFreeTicketsByMovieNameAndAddress(String movieName, String address){
        List<OrderMovieSession> ordersByMovieNameAndAddress = orderMovieSessionDAO.ordersByMovieNameAndAddress(movieName, address);

        MovieSession movieSession = findByMovieNameAndAddress(movieName, address);

        return movieSession.getTicketsCount() - ordersByMovieNameAndAddress.size();
    }
}
