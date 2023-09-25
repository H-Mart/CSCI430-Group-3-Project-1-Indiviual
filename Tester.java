import java.io.*;

public class Tester {
    private static ClientList loadSerializedClientList() {
        var file = new File("savedData.ser");

        ClientList clientList = null;

        try (var fileIn = new FileInputStream(file);
             var objectIn = new ObjectInputStream(fileIn)) {
            clientList = (ClientList) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    private static void saveClientList(ClientList clientList) {
        var file = new File("savedData.ser");

        try (var fileOut = new FileOutputStream(file);
             var objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(clientList);
            fileOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printClientList(ClientList clientList) {
        var it = clientList.getIterator();
        while (it.hasNext()) {
            var client = it.next();
            System.out.println(client);
        }
    }

    public static void main(String[] args) {
        var productTest = new Product("Product 1", 1.0, 1);
        var clientTest = new Client("Client 1", "Address 1");
        System.out.println("Testing Product:");
        System.out.println("Expecting Output:");
        System.out.println("id: 1 Price: 1.0 Quantity: 1 Name: Product 1");
        System.out.println("Actual Output:");
        System.out.println(productTest);
        System.out.println();

        System.out.println("Testing Client:");
        System.out.println("Expecting Output:");
        System.out.println("id: 3 Name: Client 1 Address: Address 1");
        System.out.println("Actual Output:");
        System.out.println(clientTest);
        System.out.println();

        var clientList = new ClientList();
        System.out.println("Testing Adding Client to ClientList:");
        System.out.println("Before Adding Client:");
        System.out.println("Expecting Output (no output expected):");
        System.out.println("Actual Output:");
        printClientList(clientList);
        System.out.println();

        clientList.insertClient(clientTest);
        System.out.println("After Adding Client:");
        System.out.println("Expecting Output:");
        System.out.println("id: 1 Name: Client 1 Address: Address 1");
        System.out.println("Actual Output:");
        printClientList(clientList);
        System.out.println();

        var file = new File("savedData.ser");
        var clientList2 = new ClientList();
        if (file.exists()) {
            System.out.println("Saved Client data found. Loading from file.");
            clientList2 = loadSerializedClientList();
            System.out.println("Loaded Client Data.");
        } else {
            System.out.println("No saved data found. Creating new data.");
            var c1 = new Client("John Doe", "123 Main St.");
            var c2 = new Client("Jane Doe", "456 Main St.");
            clientList2.insertClient(c1);
            clientList2.insertClient(c2);
            System.out.println("Added new Clients.");
            saveClientList(clientList2);
        }


        System.out.println("Testing loading ClientList from file.");
        System.out.println("Expecting Output:");
        System.out.println("id: 2 Name: John Doe Address: 123 Main St.");
        System.out.println("id: 3 Name: Jane Doe Address: 456 Main St.");
        System.out.println("Actual Output:");
        printClientList(clientList2);
        System.out.println();
    }
}
