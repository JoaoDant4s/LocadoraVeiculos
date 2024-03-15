import infra.Database;
import model.Client;

public class App {
    public static void main(String[] args) throws Exception {

        Client client = new Client("Daniel", "fdgdghrfgrb");
        Database.save(client.getFiscalDocument(), client, Client.class);
        Client clientReturned = Database.getByClassAndId(Client.class, "fdgdghrfgrb");
        if (clientReturned != null) {
            System.out.println(clientReturned.getName());
        }
    }
}
