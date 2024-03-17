package service;

import model.Rent;
import repository.RentRepository;
import repository.Repository;

public class RentService implements Service<Rent>{
    private Repository<String, Rent> rentRepository;

    public RentService() {
        this.rentRepository = new RentRepository();
    }

    @Override
    public void validate(Rent rent) throws Exception {
        if (rent == null) {
            throw new Exception("Aluguel nulo");
        }
        if (rent.getClientFiscalDocument() == null || rent.getVehicleLicensePlate() == null) {
            throw new Exception("Dados do aluguel são inconsistentes");
        }
    }

    public void rentVehicleForIndividual(Rent rent) throws Exception {
        validate(rent);
        rentRepository.save(rent);
    }

    public void rentVehicleForLegalEntity(Rent rent) throws Exception {
        validate(rent);
        rentRepository.save(rent);
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
