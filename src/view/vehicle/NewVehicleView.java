package view.vehicle;

import java.util.Scanner;

import model.MediumVehicle;
import model.SUV;
import model.SmallVehicle;
import model.Vehicle;
import service.VehicleService;
import view.View;
import view.components.Input;

public class NewVehicleView implements View{
    private final VehicleService vehicleService;
    private final Scanner scan;

    public NewVehicleView(){
        this.vehicleService = new VehicleService();
        this.scan = Input.getInstance();
    }

    private void newVehicle(Vehicle vehicle) throws Exception{
        vehicleService.createVehicle(vehicle);
    }

    private Vehicle createVehicle(){
        System.out.print("Digite o modelo do veículo: ");
        String carModel = scan.nextLine();
        System.out.print("Digite a placa do veículo: ");
        String licensePlate = scan.nextLine();
        System.out.println("1 - SUV\n2 - Médio\n3 - Pequeno");
        System.out.print("Digite o porte do veículo: ");
        Integer size = scan.nextInt();
        Vehicle vehicle = switch (size) {
            case 1 -> new SUV(licensePlate, carModel);
            case 2 -> new MediumVehicle(licensePlate, carModel);
            case 3 -> new SmallVehicle(licensePlate, carModel);
            default -> new SmallVehicle(licensePlate, carModel);
        };
        return vehicle;
    }
    @Override
    public void execute(){
        Vehicle vehicle = createVehicle();
        try{
            newVehicle(vehicle);
            System.out.println("Veículo " + vehicle.getCarModel() + " criado com sucesso!");
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
