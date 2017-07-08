package lesson11.interfaceexample;

/**
 * Created by Skorodielov on 15.06.2017.
 */
public class PostgreSQLDbProvider implements DbProvider {
    @Override
    public void connectToDb() {
        //some logic for PostgreSQL
    }

    @Override
    public void disconnectFromDb() {
        //some logic for PostgreSQL
    }

    @Override
    public void encryptData() {
        //some logic for PostgreSQL
    }
    //4
}
