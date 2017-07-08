package lesson9;

/**
 * Created by Skorodielov on 13.06.2017.
 */
public class Company {
    private String name;
    private String countryFounded;

    protected String code;

    public int someField;

    public Company(String name, String countryFounded) {
        this.name = name;
        this.countryFounded = countryFounded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryFounded() {
        return countryFounded;
    }

    public void setCountryFounded(String countryFounded) {
        this.countryFounded = countryFounded;
    }
    //4
}
