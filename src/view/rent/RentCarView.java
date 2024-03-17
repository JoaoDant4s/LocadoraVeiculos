package view.rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Rent;
import model.Vehicle;
import service.ClientService;
import service.RentService;
import service.VehicleService;
import view.View;
import view.components.Input;
import view.vehicle.SearchVehicleView;

public class RentCarView implements View {
    private final RentService rentService;
    private final VehicleService vehicleService;
    private final ClientService clientService;
    private final SearchVehicleView searchVehicleView;
    private final Scanner scan;

    public RentCarView() {
        this.rentService = new RentService();
        this.vehicleService = new VehicleService();
        this.clientService = new ClientService();
        this.searchVehicleView = new SearchVehicleView();
        this.scan = Input.getInstance();
    }

    private List<Vehicle> getAvailableVehicles(List<Vehicle> vehicles, List<Rent> rents) {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (rents.isEmpty()) {
                availableVehicles.add(vehicle);
            } else {
                for (Rent rent : rents) {
                    if (vehicle.getLicensePlate().compareTo(rent.getVehicleLicensePlate()) != 0) {
                        availableVehicles.add(vehicle);
                    }
                }
            }
        }
        return availableVehicles;
    }

    private String collectFiscalDocument() {
        System.out.print("Digite o CPF ou CNPJ do cliente: ");
        String document = scan.nextLine().trim();
        if (clientService.getClientByFiscalDocument(document) != null) {
            return document;
        } else {
            System.err.println("Não existe nenhum cliente com o documento informado");
            return collectFiscalDocument();
        }
    }

    private String collectLocal() {
        System.out.print("Digite o local da reserva: ");
        String local = scan.nextLine();
        return local;
    }

    private void rentCar(List<Vehicle> vehicles) {
        System.out.print("Digite a placa do veículo que quer alugar: ");
        String licensePlate = scan.nextLine().trim();
        if (vehicleService.getVehicleByLicensePlate(licensePlate) != null) {
            try {
                if (rentService.getRentsByLicensePlate(licensePlate).isEmpty()) {
                    String fiscalDocument = collectFiscalDocument();
                    String local = collectLocal();
                    Rent rent = new Rent(local, fiscalDocument, licensePlate.trim().toUpperCase());

                    rentService.rentVehicle(rent);
                    System.out.println("Reserva cadastrada com sucesso!");
                } else {
                    System.err.println("O veículo informado já está reservado");
                    rentCar(vehicles);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            System.err.println("Não existe nenhum veículo disponível com essa placa");
            rentCar(vehicles);
        }
    }

    @Override
    public void execute() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Rent> rents = rentService.getAllRents();
        List<Vehicle> availableVehicles = getAvailableVehicles(vehicles, rents);

        if (availableVehicles.isEmpty()) {
            System.out.println("Nenhum veículo disponível");
        } else {
            System.out.println("---Veículos disponíveis---");
            searchVehicleView.printVehicles(availableVehicles);
            rentCar(vehicles);
        }
    }
}
