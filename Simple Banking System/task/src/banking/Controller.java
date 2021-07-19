package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final ArrayList<Account> accounts;
    private CurrentMenu currentMenu = CurrentMenu.MAIN;
    private final Scanner scanner;
    private int loggedIndex = -1;

    private enum CurrentMenu {
        MAIN, LOGGED_IN
    }

    Controller(Scanner scanner) {
        accounts = new ArrayList<>();
        this.scanner = scanner;
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
                    accounts.add(new Account());
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
                    System.out.println("Balance: " + accounts.get(loggedIndex).getBalance());
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
        System.out.println("Enter your card number:");
        System.out.print(">");
        String cardNumberInput = scanner.next();
        System.out.println("Enter your PIN:");
        System.out.print(">");
        int PINInput = scanner.nextInt();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).validateCardNumber((cardNumberInput)) && accounts.get(i).validatePIN((PINInput))) {
                loggedIndex = i;
                currentMenu = CurrentMenu.LOGGED_IN;
                System.out.println("You have successfully logged in!");
                return;
            }
        }
        System.out.println("Wrong card number or PIN!");
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
