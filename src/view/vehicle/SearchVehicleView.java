package view.vehicle;

import java.util.List;
import java.util.Scanner;

import model.Vehicle;
import service.VehicleService;
import view.View;
import view.components.Input;

public class SearchVehicleView implements View{
    private final VehicleService vehicleService;
    private final Scanner scan;

    public SearchVehicleView () {
        this.vehicleService = new VehicleService();
        this.scan = Input.getInstance();
    }

    private void printVehicles(List<Vehicle> vehicles){
        System.out.println("Porte  | Placa   | Modelo   | Preço para alugar");
        for(Vehicle vehicle : vehicles){
            Double vehicleRentCost = vehicle.getDailyRentCost();
            String vehicleType = vehicleRentCost == 100 ? "Pequeno" :
                vehicleRentCost == 150 ? "Médio" :
                vehicleRentCost == 200 ? "SUV" :
                "Desconhecido";
            System.out.println(
                vehicleType + " | " + 
                vehicle.getLicensePlate() + " | " + 
                vehicle.getCarModel() + " | " + 
                vehicleRentCost
            );
        }
    }

    private void searchVehicle() throws Exception{
        System.out.print("Digite o modelo do veículo: ");
        String name = scan.nextLine();
        List<Vehicle> vehicles = vehicleService.findVehiclesByName(name);
        if(!vehicles.isEmpty()){
            System.out.println("---Veículos encontrados---");
            printVehicles(vehicles);
        } else {
            System.out.println("Nenhum veículo encontrado");
        }
    }

    @Override
    public void execute(){
        try{
            searchVehicle();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
