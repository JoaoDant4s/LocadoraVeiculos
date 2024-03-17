package service;

public interface Service<T> {
    void validate(T object) throws Exception;
}
