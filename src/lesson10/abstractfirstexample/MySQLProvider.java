package lesson10.abstractfirstexample;

/**
 * Created by Skorodielov on 15.06.2017.
 */
public class MySQLProvider extends DbProvider{

    //public MySQLProvider(String dbHost) {
    //    super(dbHost);
    //}

    @Override
    void connectToDb(){
        //logic for MySQL
    }

    @Override
    void disconnectFromDb() {

    }
    //4
}
