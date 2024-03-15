package infra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.Client;
import model.Rent;
import model.Vehicle;

public class Database {
    private static final Hashtable<String, Client> clients = new Hashtable<>();
    private static final Hashtable<String, Vehicle> vehicles = new Hashtable<>();
    private static final Hashtable<String, Rent> rents = new Hashtable<>();

    private static <T> Hashtable<String, T> getTable(Class<T> clazz) {
        if (clazz == Client.class) {
            return (Hashtable<String, T>) clients;
        } else if (clazz == Rent.class) {
            return (Hashtable<String, T>) rents;
        } else if (Vehicle.class.isAssignableFrom(clazz)) {
            return (Hashtable<String, T>) vehicles;
        } else {
            throw new IllegalArgumentException("Unsupported class: " + clazz.getName());
        }
    }

    public static <T> void save(String identifier, T object, Class<T> clazz) {
        Hashtable<String, T> ht = getTable(clazz);
        ht.put(identifier, object);
    }

    public static <T> void delete(Class<T> clazz, String identifier) {
        Hashtable<String, T> ht = getTable(clazz);
        ht.remove(identifier);
    }

    public static <T> List<T> getAllByClass(Class<T> clazz) {
        Hashtable<String, T> ht = getTable(clazz);
        return new ArrayList<>(ht.values());
    }

    public static <T> T getByClassAndId(Class<T> clazz, String identifier) {
        Hashtable<String, T> ht = getTable(clazz);
        return ht.get(identifier);
    }
}
