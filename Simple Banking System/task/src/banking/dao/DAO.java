package banking.dao;

public interface DAO<T> {
    boolean add(T element);
    T findByNumberAndPin(String number, String pin);
    boolean addToBalance(int income, String number);
    boolean transferMoney(int amount, String fromNumber, String toNumber);
//    boolean subtractFromBalance(int amount, String number);
    boolean delete(T element);
    boolean exists(String number);
}
