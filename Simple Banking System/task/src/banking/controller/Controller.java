package banking.controller;

import banking.dao.JdbcCardDAO;
import banking.model.Card;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private CurrentMenu currentMenu = CurrentMenu.MAIN;
    private final Scanner scanner;
    private final JdbcCardDAO jdbcCardDAO;
    private Card card; // card that's currently logged in

    private enum CurrentMenu {
        MAIN, LOGGED_IN
    }

    public Controller(Scanner scanner, JdbcCardDAO jdbcCardDAO) {
        this.scanner = scanner;
        this.jdbcCardDAO = jdbcCardDAO;
    }

    public void run() {
        printCurrentMenu();
        System.out.print(">");
        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println("Input is not a digit.");
            scanner.nextLine();
            return;
        }

        if (currentMenu == CurrentMenu.MAIN) {
            switch (choice) {
                case 0:
                    System.out.println("BYE!");
                    System.exit(0);
                    break;
                case 1:
                    Card card = new Card();
                    if (!jdbcCardDAO.add(card)) System.out.println("Operation aborted.");
                    break;
                case 2:
                    logIn();
                    break;
                default:
                    System.out.println("Action unsupported!");
                    break;
            }
            return;
        }

        if (currentMenu == CurrentMenu.LOGGED_IN) {
            switch (choice) {
                case 0:
                    System.out.println("BYE!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Balance: " + card.getBalance());
                    break;
                case 2:
                    depositFunds();
                    break;
                case 3:
                    transferFunds();
                    break;
                case 4:
                    if (jdbcCardDAO.delete(card)) {
                        System.out.println("The account has been closed!");
                        currentMenu = CurrentMenu.MAIN;
                    } else {
                        System.out.println("Operation aborted.");
                    }
                    break;
                case 5:
                    System.out.println("You have successfully logged out!");
                    currentMenu = CurrentMenu.MAIN;
                    break;
                default:
                    System.out.println("Action unsupported!");
                    break;
            }
        }
    }

    private void logIn() {
        System.out.print("Enter your card number:\n>");
        String cardNumberInput = scanner.next();
        System.out.print("Enter your pin:\n>");
        String pinInput = scanner.next();

        card = jdbcCardDAO.findByNumberAndPin(cardNumberInput, pinInput);
        if (card.getBalance() == -1) {
            System.out.println("Wrong card number or PIN!");
            return;
        }
        currentMenu = CurrentMenu.LOGGED_IN;
        System.out.println("You have successfully logged in!");
    }

    private void depositFunds() {
        System.out.print("Enter income:\n>");
        int income = scanner.nextInt();
        if (card.addToBalance(income)) {
            if (jdbcCardDAO.addToBalance(income, card.getNumber())) {
                System.out.println("Income was added!");
            } else {
                System.out.println("Operation aborted.");
            }

        } else {
            System.out.println("Income must be a positive integer number.");
        }
    }

    private void transferFunds() {
        System.out.print("Transfer\nEnter card number:\n>");
        String cardNumberInput = scanner.next();
        if (!Card.validateChecksum(cardNumberInput)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        if (!jdbcCardDAO.exists(cardNumberInput)) {
            System.out.println("Such a card does not exist.");
        }
        System.out.println("Enter how much money you want to transfer:\n>");
        int amount = scanner.nextInt();
        if (card.subtractFromBalance(amount)) {
            if (jdbcCardDAO.transferMoney(amount, card.getNumber(), cardNumberInput)) {
                System.out.println("Success!");
            } else {
                System.out.println("Operation aborted.");
            }
        } else {
            System.out.println("Not enough money!");
        }
    }

    private void printCurrentMenu() {
        if (currentMenu == CurrentMenu.MAIN) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
        }
        if (currentMenu == CurrentMenu.LOGGED_IN) {
            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");
        }
    }

}
