package dz_lesson35_36.demo;

import dz_lesson35_36.dao.UserDAO;
import dz_lesson35_36.model.User;
import dz_lesson35_36.model.UserType;

public class DemoUser {
    public static void main(String[] args)throws Exception {

        User user1 = new User("Nik", "1111", "Ukraine", UserType.USER);
        User user2 = new User("Andre", "2222", "Ukraine", UserType.ADMIN);
        User user3 = new User("Bob", "3333", "Ukraine", UserType.USER);
        User user4 = new User("Tad", "4444", "Germany", UserType.USER);

        UserDAO.registerUser(user4);
        //UserDAO.registerUser(user2);
        //UserDAO.registerUser(user3);


    }
}
