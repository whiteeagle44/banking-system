package banking.dao;

import banking.model.Card;

public interface CardDAO extends DAO<Card>{
    String TABLE_NAME = "card";
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + "	id INTEGER PRIMARY KEY NOT NULL,"
            + "	number TEXT NOT NULL,"
            + "	pin TEXT NOT NULL,"
            + "	balance INTEGER DEFAULT 0"
            + ");";
    String INSERT = "INSERT INTO " + TABLE_NAME + "(number, pin) VALUES (?, ?);";
    String FIND_BY_NUMBER_AND_PIN = "SELECT * FROM " + TABLE_NAME + " WHERE number=? AND pin=?;";
}
