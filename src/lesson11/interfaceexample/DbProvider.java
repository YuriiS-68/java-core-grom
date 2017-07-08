package lesson11.interfaceexample;

/**
 * Created by Skorodielov on 24.06.2017.
 */
public interface DbProvider {

    void connectToDb();

    void disconnectFromDb();

    void encryptData();
    //4
}
