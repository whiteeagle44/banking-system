package banking.dao;

public interface DAO<T> {
    void add(T element);
    T findByNumberAndPin(String number, String pin);
}
