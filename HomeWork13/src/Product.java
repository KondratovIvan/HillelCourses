import java.time.LocalDate;

public class Product {
    private String type;
    private double price;
    private boolean isSale;
    private  LocalDate addDate;

    public Product(String type, double price, boolean isSale, LocalDate addDate) {
        this.type = type;
        this.price = price;
        this.isSale = isSale;
        this.addDate = addDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", isSale=" + isSale +
                ", addDate=" + addDate +
                '}';
    }
}
