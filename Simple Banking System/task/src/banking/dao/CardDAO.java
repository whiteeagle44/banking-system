package banking.dao;

import banking.model.Card;

import java.util.SplittableRandom;

public interface CardDAO extends DAO<Card>{
    String TABLE_NAME = "card";
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + "	id INTEGER PRIMARY KEY NOT NULL,"
            + "	number TEXT NOT NULL,"
            + "	pin TEXT NOT NULL,"
            + "	balance INTEGER DEFAULT 0"
            + ");";
    String INSERT = "INSERT INTO " + TABLE_NAME + "(number, pin) VALUES (?, ?);";
    String FIND_BY_NUMBER_AND_PIN = "SELECT * FROM " + TABLE_NAME + " WHERE number = ? AND pin = ?;";
    String ADD_TO_BALANCE = "UPDATE " + TABLE_NAME +
            " SET balance = balance + ? WHERE number = ?;";
    String SUBTRACT_FROM_BALANCE = "UPDATE " + TABLE_NAME +
            " SET balance = balance - ? WHERE number = ?;";
    String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE number = ? AND pin = ?;";
    String FIND_BY_NUMBER = "SELECT * FROM " + TABLE_NAME + " WHERE number = ?;";
}
