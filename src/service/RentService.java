package service;

import java.util.List;

import model.Rent;
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

    public void rentVehicle(Rent rent) throws Exception {
        validate(rent);
        createRent(rent);
    }

    public void returnVehicleForIndividual(String rentId) throws Exception {
        Rent rent = rentRepository.getByKey(rentId);
        if (rent != null) {
            rentRepository.delete(rentId);
        } else {
            throw new Exception("Aluguel com o ID " + rentId + " não encontrado.");
        }
    }

    public void returnVehicleForLegalEntity(String rentId) throws Exception {
        Rent rent = rentRepository.getByKey(rentId);
        if (rent != null) {
            rentRepository.delete(rentId);
        } else {
            throw new Exception("Aluguel com o ID " + rentId + " não encontrado.");
        }
    }
}
