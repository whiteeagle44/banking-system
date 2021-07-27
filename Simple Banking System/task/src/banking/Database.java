package banking;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private final String url;

    Database(String fileName) {
        url = "jdbc:sqlite:" + fileName;

        String sql = "CREATE TABLE IF NOT EXISTS card ("
                + "	id INTEGER PRIMARY KEY NOT NULL,"
                + "	number TEXT NOT NULL,"
                + "	pin TEXT NOT NULL,"
                + "	balance INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql); // create a new table
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(Account account) {
        String sql = "INSERT INTO card(number, pin) VALUES (?, ?);";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getCardNumber());
            pstmt.setString(2, String.valueOf(account.getPin()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fetchFromDatabase(ArrayList<Account> accounts) {
        String sql = "SELECT * FROM card";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
//                int id = rs.getInt("id");
                String number = rs.getString("number");
                int pin = Integer.parseInt(rs.getString("pin"));
                int balance = rs.getInt("balance");
                accounts.add(new Account(number, pin, balance));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

