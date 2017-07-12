package lesson13;

/**
 * Created by Skorodielov on 08.07.2017.
 */
public class UserRepository {
     private User[] users = new User[10];

    public User[] getUsers() {
        return users;
    }

    public User save(User user){
        if (user == null)
            return null;

        if (findById(user.getId()) != null)
            return null;

        int countPlaced = 0;
        for (User user1 : users) {
            if (user1 != null)
                countPlaced++;
        }

        if (countPlaced > 9)
            return null;

        int index = 0;
        for (User user1 : users) {
            if (user1 == null) {
                users[index] = user;
                break;
            }
            index++;
        }
        return user;
    }

    public User update(User user){
        if (user == null)
            return null;

        int index = 0;
        for (User user1 : users) {
            if (user1 == findById(user.getId())){
                users[index] = user;
                break;
            }
            index++;
        }
        return user;
    }

    public void delete(long id){
        User user = findById(id);

        int index = 0;
        for (User user1 : users) {
            if (user1 == findById(user.getId())){
                users[index] = null;
                break;
            }
            index++;
        }
    }

    private User findById(long id){
        for (User user : users) {
            if(user != null && id == user.getId())
                return user;
        }
        return null;
    }
}
