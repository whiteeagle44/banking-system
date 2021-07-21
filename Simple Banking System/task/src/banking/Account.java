package banking;

import java.util.Random;

public class Account {
    private final Card card;
    private int balance = 0;

    private class Card {
        private final int BIN = 400000;
        private final int accountIdentifier;
        private final int CHECKSUM;
        private final int PIN;

        public Card() {
            System.out.println("Your card has been created");
            accountIdentifier = new Random().nextInt(999_999_999 - 100_000_000 + 1) + 100_000_000;
            CHECKSUM = generateChecksum();
            System.out.println("Your card number:");
            System.out.println(getCardNumber());
            PIN = generatePIN();
            System.out.println("Your card PIN:");
            System.out.println(PIN);
        }

        private int generateChecksum() {
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

        private String getCardNumber() {
            return "" + BIN + accountIdentifier + CHECKSUM;
        }

        private int generatePIN() {
            return new Random().nextInt(9999 - 1000 + 1) + 1000;
        }
    }

    public Account() {
        card = new Card();
    }

    public boolean validateCardNumber(String cardNumberInput) {
        return card.getCardNumber().equals(cardNumberInput);
    }

    public boolean validatePIN(int PINInput) {
        return card.PIN == PINInput;
    }

    public int getBalance() {
        return balance;
    }
}