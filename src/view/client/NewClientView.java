package view.client;

import java.util.Scanner;

import model.Client;
import model.ClientType;
import service.ClientService;
import view.View;
import view.components.Input;

public class NewClientView implements View{
    private final ClientService clientService;
    private final Scanner scan;

    public NewClientView(){
        this.clientService = new ClientService();
        this.scan = Input.getInstance();
    }

    private void newClient(Client client) throws Exception{
        clientService.createClient(client);
    }

    private Client createClient() {
        System.out.print("Digite o nome do cliente: ");
        String clientName = scan.nextLine();
        System.out.print("1 - PF\n2 - PJ\n");
        Integer choice = scan.nextInt();
        scan.nextLine();
        ClientType clientType = switch (choice) {
            case 1 -> ClientType.PF;
            case 2 -> ClientType.PJ;
            default -> ClientType.PF;
        };
        System.out.print("Digite o " + (clientType == ClientType.PF ? "CPF" : "CNPJ") + " do cliente " + clientName + ": ");
        String fiscalDocument = scan.nextLine();

        return new Client(clientName, fiscalDocument, clientType);
    }

    @Override
    public void execute(){
        Client client = createClient();
        try{
           newClient(client);
           System.out.println("Cliente " + client.getName() + " criado com sucesso!");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
