package repository;

import java.util.List;
import infra.Database;
import model.Rent;

public class RentRepository implements Repository<String, Rent> {
    @Override
    public void save(Rent rent) {
        String rentId = rent.getLocalDateTime().toString();
        Database.save(rentId, rent, Rent.class);
    }

    @Override
    public List<Rent> getAll() {
        List<Rent> rents = Database.getAllByClass(Rent.class);
        return rents;
    }

    @Override
    public Rent getByKey(String rentId) {
        return Database.getByClassAndId(Rent.class, rentId);
    }

    @Override
    public void delete(String rentId) {
        Database.delete(Rent.class, rentId);
    }
}
