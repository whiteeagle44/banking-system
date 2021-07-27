package banking.dao;

import banking.model.Card;

import java.sql.*;

public class JdbcCardDAO implements CardDAO{
    private final String url;

    public JdbcCardDAO(String fileName) {
        url = "jdbc:sqlite:Simple Banking System\\task\\" + fileName;

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void add(Card card) {
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(INSERT)) {
            pstmt.setString(1, card.getNumber());
            pstmt.setString(2, card.getPin());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Card findByNumberAndPin(String number, String pin) {
        int balance = -1;
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(FIND_BY_NUMBER_AND_PIN)) {

            pstmt.setString(1, number);
            pstmt.setString(2, pin);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    balance = rs.getInt("balance");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Card(number, pin, balance);
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

}

