package dz_lesson30;

public class Projects {
    private String name;
    private Customer customer;

    public Projects(String name, Customer customer) {
        this.name = name;
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projects projects = (Projects) o;

        if (!name.equals(projects.name)) return false;
        return customer.equals(projects.customer);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + customer.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "name='" + name + '\'' +
                ", customer=" + customer +
                '}';
    }
}
