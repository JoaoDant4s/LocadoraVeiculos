package service;

import java.util.List;

import model.Client;
import repository.ClientRepository;
import repository.Repository;

public class ClientService{
    private Repository<String, Client> clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void validate(Client client) throws Exception {
        if (client == null) {
            throw new Exception("Cliente nulo");
        }
        if (client.getName() == null || client.getFiscalDocument() == null) {
            throw new Exception("Dados do cliente são inconsistentes");
        }
    }

    public List<Client> getAllClients(){
        return clientRepository.getAll();
    }

    public Client getClientByFiscalDocument(String fiscalDocument){
        return clientRepository.getByKey(fiscalDocument);
    }

    public void createClient(Client client) throws Exception {
        validate(client);
        clientRepository.save(client);
    }

    public void updateClient(String fiscalDocument, Client updatedClient) throws Exception {
        if(fiscalDocument == null) throw new Exception("Documento fiscal informado é nulo");
        if(updatedClient == null) throw new Exception("Cliente nulo");
        if (clientRepository.getByKey(fiscalDocument) != null) {
            clientRepository.save(updatedClient);
        } else {
            throw new Exception("Cliente com o documento fiscal " + fiscalDocument + " não foi encontrado.");
        }
    }
}
