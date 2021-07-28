package banking.model;

import java.util.Random;

public class Card {
    private final String pin;
    private final String number;
    private int balance = 0;

    public Card() {
        number = generateCardNumber();
        pin = generatePin();
        System.out.println("Your card has been created.\nYour card number:\n" + number);
        System.out.println("Your card PIN:\n" + pin);
    }

    public String getPin() {
        return pin;
    }

    public String getNumber() {
        return number;
    }

    public int getBalance() {
        return balance;
    }


    public Card(String pin, String number, int balance) {
        this.pin = pin;
        this.number = number;
        this.balance = balance;
    }

    public boolean validatePIN(String PIN) {
        return this.pin.equals(PIN);
    }

    public boolean validateNumber(String number) {
        return this.number.equals(number);
    }

    private String generatePin() {
        return String.valueOf(new Random().nextInt(9999 - 1000 + 1) + 1000); // in range <1000, 9999>
    }

    private String generateCardNumber() {
        String BIN = "400000";
        String accountIdentifier = String.valueOf(new Random().nextInt(999_999_999 - 100_000_000 + 1) + 100_000_000); // in range <100_000_000, 999_999_999>
        int checksum = generateChecksum(BIN + accountIdentifier);
        return BIN + accountIdentifier + checksum;
    }

    public boolean addToBalance(int income) {
        if (income > 0) {
            balance += income;
            return true;
        }
        return false;
    }

    public boolean subtractFromBalance(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public static boolean validateChecksum(String number) {
        return generateChecksum(number.substring(0, number.length() - 1))
                == Integer.parseInt(number.substring(number.length() - 1, number.length()));
    }

    // implements Luhn's algorithm
    private static int generateChecksum(String currentNumber) {
        int sum = 0;
        for (int i = 0; i < currentNumber.length(); i++) {
            int num = Character.getNumericValue(currentNumber.charAt(i));
            if ( (i + 1) % 2 != 0) {
                num *= 2;
                if (num > 9) {
                    sum += num - 9;
                } else {
                    sum += num;
                }
            } else {
                sum += num;
            }
        }
        return 10 - (sum % 10) != 10 ? 10 - (sum % 10) : 0;
    }

}
