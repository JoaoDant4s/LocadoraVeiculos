package repository;

import java.util.List;
import infra.Database;
import model.Client;

public class ClientRepository implements Repository<String, Client> {
    @Override
    public void save(Client client) {
        Database.save(client.getFiscalDocument(), client, Client.class);
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = Database.getAllByClass(Client.class);
        return clients;
    }

    @Override
    public Client getByKey(String fiscalDocument) {
        return Database.getByClassAndId(Client.class, fiscalDocument);
    }

    @Override
    public void delete(String fiscalDocument) {
        Database.delete(Client.class, fiscalDocument);
    }
}
