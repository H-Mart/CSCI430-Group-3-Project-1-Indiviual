import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class ClientList implements Serializable {
    private static final long serialVersionUID = 1L;

    // singleton class for storing clients
    private final ArrayList<Client> clientArrayList;

    public ClientList() {
        clientArrayList = new ArrayList<>();
    }

    public Optional<Client> getClientById(String clientId) {
        for (Client client : clientArrayList) {
            if (client.getId().equals(clientId)) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

    public Iterator<Client> getIterator() {
        return clientArrayList.iterator();
    }

    public boolean insertClient(Client client) {
        clientArrayList.add(client);
        return true;
    }

    public boolean clientIdInList(String clientId) {
        for (Client client : clientArrayList) {
            if (client.getId().equals(clientId)) {
                return true;
            }
        }
        return false;
    }
}
