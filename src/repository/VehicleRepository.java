package repository;

import java.util.List;

import infra.Database;
import model.Vehicle;

public class VehicleRepository implements Repository<String, Vehicle> {
    @Override
    public void save(Vehicle vehicle) {
        Database.save(vehicle.getLicensePlate(), vehicle, Vehicle.class);
    }

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> vehicles = Database.getAllByClass(Vehicle.class);
        return vehicles;
    }

    @Override
    public Vehicle getByKey(String licensePlate) {
        return Database.getByClassAndId(Vehicle.class, licensePlate);
    }

    @Override
    public void delete(String licensePlate) {
        Database.delete(Vehicle.class, licensePlate);
    }
}
