import java.io.IOException;
import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private double price;

    private int quantity;

    private static int nextId = 1;

    /**
     * @param name     name of product
     * @param price    price of product
     * @param quantity quantity of product
     */
    public Product(String name, double price, int quantity) {
        this.id = Integer.toString(nextId++);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // private attribute getters

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "id: " + id + " Price: " + price + " Quantity: " + quantity + " Name: " + name;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(nextId);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        nextId = (int) in.readObject();
    }
}
