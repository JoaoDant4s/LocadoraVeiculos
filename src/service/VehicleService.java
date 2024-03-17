package service;

import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
import repository.Repository;
import repository.VehicleRepository;

public class VehicleService implements Service<Vehicle>{
    private Repository<String, Vehicle> vehicleRepository;

    public VehicleService() {
        this.vehicleRepository = new VehicleRepository();
    }

    @Override
    public void validate(Vehicle vehicle) throws Exception{
        if(vehicle == null){
            throw new Exception("Veículo nulo");
        }
        if(vehicle.getCarModel() == null || vehicle.getDailyRentCost() == null || vehicle.getLicensePlate() == null){
            throw new Exception("Dados do veículo são inconsistentes");
        }
    }

    public void createVehicle(Vehicle vehicle) throws Exception {
        try {
            validate(vehicle);
        } catch (Exception e) {
            throw e;
        }
        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(String licensePlate, Vehicle updatedVehicle) throws Exception {
        if (vehicleRepository.getByKey(licensePlate) != null) {
            vehicleRepository.save(updatedVehicle);
        } else {
            throw new Exception("Veículo com a placa " + licensePlate + " não encontrado.");
        }
    }

    public List<Vehicle> findVehiclesByName(String name) {
        List<Vehicle> vehiclesFound = new ArrayList<>();
        List<Vehicle> allVehicles = vehicleRepository.getAll();
        
        for (Vehicle vehicle : allVehicles) {
            if (vehicle.getCarModel().contains(name)) {
                vehiclesFound.add(vehicle);
            }
        }
        
        return vehiclesFound;
    }
}
