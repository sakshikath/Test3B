import javafx.beans.property.*;

public class PizzaOrder {
    private final StringProperty name, mobile, size;
    private final IntegerProperty toppings;
    private final DoubleProperty bill;

    public PizzaOrder(String name, String mobile, String size, int toppings, double bill) {
        this.name = new SimpleStringProperty(name);
        this.mobile = new SimpleStringProperty(mobile);
        this.size = new SimpleStringProperty(size);
        this.toppings = new SimpleIntegerProperty(toppings);
        this.bill = new SimpleDoubleProperty(bill);
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty mobileProperty() { return mobile; }
    public StringProperty sizeProperty() { return size; }
    public IntegerProperty toppingsProperty() { return toppings; }
    public DoubleProperty billProperty() { return bill; }
}
