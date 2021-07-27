package banking.controller;

import banking.dao.JdbcCardDAO;
import banking.model.Card;

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
        if (currentMenu == CurrentMenu.MAIN) {
            switch (scanner.nextInt()) {
                case 0:
                    System.out.println("BYE!");
                    System.exit(0);
                    break;
                case 1:
                    Card card = new Card();
                    jdbcCardDAO.add(card);
                    break;
                case 2:
                    logIn();
                    break;
                default:
                    printCurrentMenu();
                    break;
            }
            return;
        }

        if (currentMenu == CurrentMenu.LOGGED_IN) {
            switch (scanner.nextInt()) {
                case 0:
                    System.out.println("BYE!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Balance: " + card.getBalance());
                    break;
                case 2:
                    System.out.println("You have successfully logged out!");
                    currentMenu = CurrentMenu.MAIN;
                    break;
                default:
                    printCurrentMenu();
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

    private void printCurrentMenu() {
        if (currentMenu == CurrentMenu.MAIN) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
        }
        if (currentMenu == CurrentMenu.LOGGED_IN) {
            System.out.println("1. Balance");
            System.out.println("2. Log out");
            System.out.println("0. Exit");
        }
    }

}
