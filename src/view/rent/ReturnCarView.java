package view.rent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.Rent;
import service.ClientService;
import service.RentService;
import service.VehicleService;
import view.View;
import view.components.Input;

public class ReturnCarView implements View{
    private final RentService rentService;
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final Scanner scan;
    
    public ReturnCarView(){
        this.rentService = new RentService();
        this.clientService = new ClientService();
        this.vehicleService = new VehicleService();
        this.scan = Input.getInstance();
    }

    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM - HH:mm");
        return dateTime.format(formatter);
    }


    public void printRents(List<Rent> rents){
        System.out.println("Cliente  | Carro  | Data e Hora  | Local");
        for(Rent rent : rents){
            System.out.println(
                rent.getClientFiscalDocument() + " | " + 
                rent.getVehicleLicensePlate() + " | " +
                formatDate(rent.getLocalDateTime()) + " | " +
                rent.getLocal()
            );
        }
    }

    private String collectDocument(){
        System.out.print("Digite o CPF ou CNPJ do cliente que fez a reserva: ");
        String document = scan.nextLine().trim();
        if(clientService.getClientByFiscalDocument(document) != null){
            return document;
        } else {
            System.err.println("Não existe nenhum cliente com o documento informado");
            return collectDocument();
        }
    }

    private String collectLicensePlate(List<Rent> clientRents){
        System.out.print("Digite a placa do veículo a ser devolvido: ");
        String licensePlate = scan.nextLine().trim().toUpperCase();
        boolean licensePlateIsValid = false;
        for(Rent rent : clientRents){
            if(rent.getVehicleLicensePlate().equalsIgnoreCase(licensePlate)){
                licensePlateIsValid = true;
            }
        }
        if(licensePlateIsValid){
            return licensePlate;
        } else {
            System.err.println("O cliente reservou nenhum veículo com a placa informada");
            return collectLicensePlate(clientRents);
        }
    }
    @Override
    public void execute(){
        String document = collectDocument();
        List<Rent> clientRents = null;
        try {
            clientRents = rentService.getRentsByClientFiscalDocument(document);
            if(!clientRents.isEmpty()){
                System.out.println("---Reservas do Cliente---");
                printRents(clientRents);
                String licensePlate = collectLicensePlate(clientRents);
                List<Rent> rents = rentService.getRentsByLicensePlate(licensePlate);
                rentService.returnVehicle(rents.get(0).getLocalDateTime().toString());
                System.out.println("Veículo devolvido com sucesso!");
                Integer daysToPay = rentService.daysToPay(rents.get(0).getLocalDateTime());
                System.out.println("Dias decorridos: " + daysToPay);
                Double totalToPay = rentService.calculateRentCost(vehicleService.getVehicleByLicensePlate(licensePlate), daysToPay);
                System.out.println("Total a pagar: " + totalToPay);
            } else {
                System.err.println("Nenhuma cadastrada para o cliente informado");
                execute();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
