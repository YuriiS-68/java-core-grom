package lesson11.interfaceexample;

/**
 * Created by Skorodielov on 24.06.2017.
 */
public class MySQLProvider implements DbProvider {
    @Override
    public void connectToDb() {
        //some logic for mysql
    }

    @Override
    public void disconnectFromDb() {
        //some logic for mysql
    }

    @Override
    public void encryptData() {
        //some logic for mysql
    }
    //4
}
