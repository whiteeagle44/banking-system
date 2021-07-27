package banking;

import java.util.Random;

public class Account {
    private final Card card;
    private int balance = 0;

    public Account(String number, int pin, int balance) {
        this.balance = balance;
        card = new Card(number, pin);
    }

    private class Card {
        private final int PIN;
        private final String number;

        public Card() {
            System.out.println("Your card has been created");
            System.out.println("Your card number:");
            this.number = generateCardNumber();
            System.out.println(number);
            PIN = generatePIN();
            System.out.println("Your card PIN:");
            System.out.println(PIN);
        }

        public Card(String number, int PIN) {
            this.number = number;
            this.PIN = PIN;
        }

        private int generateChecksum(int BIN, int accountIdentifier) {
            String currentNumber = "" + BIN + accountIdentifier;
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

        public String generateCardNumber() {
            int BIN = 400000;
            int accountIdentifier = new Random().nextInt(999_999_999 - 100_000_000 + 1) + 100_000_000;
            int checksum = generateChecksum(BIN, accountIdentifier);
            return "" + 400000 + accountIdentifier + checksum;
        }

        public int getPIN() {
            return PIN;
        }

        public String getNumber() {
            return number;
        }

        private int generatePIN() {
            return new Random().nextInt(9999 - 1000 + 1) + 1000;
        }
    }

    public Account() {
        card = new Card();
    }

    public boolean validateCardNumber(String cardNumberInput) {
        return card.getNumber().equals(cardNumberInput);
    }

    public boolean validatePIN(int PINInput) {
        return card.PIN == PINInput;
    }

    public int getBalance() {
        return balance;
    }

    public String getCardNumber() {
        return card.getNumber();
    }

    public int getPin() {
        return card.getPIN();
    }


}