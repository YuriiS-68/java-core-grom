package Awebinar;

public class Controller {

    private MovieSessionDAO movieSessionDAO = new MovieSessionDAO();
    private OrderMovieSessionDAO orderMovieSessionDAO = new OrderMovieSessionDAO();

    public void orderTicketMovieSession(User user, String movieName, String address, int ticketsCount){
        //find movieSession
        //check free tickets
        //order
        MovieSession movieSession = movieSessionDAO.findByMovieNameAndAddress(movieName, address);

        //TODO
        if (movieName == null)
            //return error

        if (movieSessionDAO.findFreeTicketsByMovieNameAndAddress(movieName, address) >= ticketsCount){
            OrderMovieSession orderMovieSession = new OrderMovieSession(movieSession, user);
            orderMovieSessionDAO.createOrder(orderMovieSession);
            //return status successful
        }
        else {
            //return error: no free tickets
        }
    }
}
