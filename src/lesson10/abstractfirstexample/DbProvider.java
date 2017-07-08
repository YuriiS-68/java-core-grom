package lesson10.abstractfirstexample;

/**
 * Created by Skorodielov on 15.06.2017.
 */
public abstract class DbProvider {

    private String dbHost;

    //public DbProvider(String dbHost) {
    //    this.dbHost = dbHost;
    //}

    abstract void connectToDb();

    abstract void disconnectFromDb();

    void printDbHost(){
        System.out.println("DB host is " + dbHost);
    }
    //4
}
