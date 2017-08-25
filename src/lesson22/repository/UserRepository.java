package lesson22.repository;

public class UserRepository {
    private static User[] users = new User[10];

    public static User save(User user){
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

    public static User update(User user){
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

    public static void delete(long id){
        int index = 0;
        for (User user1 : users) {
            if (user1 == findById(user1.getId())){
                users[index] = null;
                break;
            }
            index++;
        }
    }

    private static User findById(long id){
        for (User user : users) {
            if(user != null && id == user.getId())
                return user;
        }
        return null;
    }

    public static User[] getUsers() {
        return users;
    }
}
