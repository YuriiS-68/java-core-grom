package dz_lesson35_36.service;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.User;
import dz_lesson35_36.dao.UserDAO;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User registerUser(User user)throws Exception{
        //check business logic
        if (user == null)
            throw new BadRequestException("This " + user + " is not exist");

        //if logic is ok
        return userDAO.registerUser(user);

    }
}
