package banking.dao;

public interface DAO<T> {
    void add(T element);
    T findByNumberAndPin(String number, String pin);
    void addToBalance(int income, String number);
    void subtractFromBalance(int amount, String number);
    void delete(T element);
    boolean exists(String number);
}
