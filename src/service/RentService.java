package service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.ClientType;
import model.Rent;
import model.Vehicle;
import repository.RentRepository;
import repository.Repository;

public class RentService{
    private Repository<String, Rent> rentRepository;

    public RentService() {
        this.rentRepository = new RentRepository();
    }

    public void validate(Rent rent) throws Exception {
        if (rent == null) {
            throw new Exception("Aluguel nulo");
        }
        if (rent.getClientFiscalDocument() == null || rent.getVehicleLicensePlate() == null) {
            throw new Exception("Dados do aluguel são inconsistentes");
        }
    }

    private void createRent(Rent rent) throws Exception{
        rentRepository.save(rent);
    }

    public List<Rent> getAllRents(){
        return rentRepository.getAll();
    }

    public List<Rent> getRentsByClientFiscalDocument(String fiscalDocument) throws Exception{
        if(fiscalDocument.isEmpty()) throw new Exception("Documento inválido");
        List<Rent> rents = getAllRents();
        List<Rent> clientRents = new ArrayList<>();

        for(Rent rent : rents){
            if(rent.getClientFiscalDocument().equalsIgnoreCase(fiscalDocument)){
                clientRents.add(rent);
            }
        }

        return clientRents;
    }

    public List<Rent> getRentsByLicensePlate(String licensePlate) throws Exception{
        if(licensePlate.isEmpty()) throw new Exception("Placa inválida");
        List<Rent> rents = getAllRents();
        List<Rent> vehicleRents = new ArrayList<>();

        for(Rent rent : rents){
            if(rent.getVehicleLicensePlate().equalsIgnoreCase(licensePlate)){
                vehicleRents.add(rent);
            }
        }

        return vehicleRents;
    }

    public Integer daysToPay(LocalDateTime rentDateTime){
        Duration rentDuration = Duration.between(rentDateTime, LocalDateTime.now());
        long hours = rentDuration.toHours();

        if (hours % 24 == 0) {
            return (int) (hours / 24);
        } else {
            return (int) (hours / 24) + 1;
        }
    }

    public Double calculateRentCost(Vehicle vehicle, Integer daysToPay, Client client) throws Exception {
        if (vehicle == null || daysToPay == null || client == null) {
            throw new Exception("Dados inválidos");
        }

        double totalCost = vehicle.getDailyRentCost() * daysToPay;

        if (client.getType() == ClientType.PF && daysToPay > 5) {
            totalCost *= 0.95;
        } else if (client.getType() == ClientType.PJ && daysToPay > 3) {
            totalCost *= 0.90;
        }

        return totalCost;
    }

    public void rentVehicle(Rent rent) throws Exception {
        validate(rent);
        createRent(rent);
    }

    public void returnVehicle(String rentId) throws Exception {
        Rent rent = rentRepository.getByKey(rentId);
        if (rent != null) {
            rentRepository.delete(rentId);
        } else {
            throw new Exception("Aluguel com o ID " + rentId + " não encontrado.");
        }
    }
}
