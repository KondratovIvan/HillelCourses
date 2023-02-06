public class Cafe {
    private String name;
    private String address;
    private int drinks;

    public Cafe() {
    }

    public Cafe(String name, String address, int drinks) {
        this.name = name;
        this.address = address;
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", drinks=" + drinks +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setDrinks(int drinks) {
        this.drinks = drinks;
    }
}
