import java.io.IOException;
import java.io.Serializable;


public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String name;
    private final String address;

    private static int nextId = 1;

    /**
     * @param name    the name of the client
     * @param address the address of the client
     * @precondition name and address are not null
     * @postcondition id is set to nextId, nextId is incremented
     */
    public Client(String name, String address) {
        this.id = Integer.toString(nextId++);
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + id + " Name: " + name + " Address: " + address;
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