package banking;

import banking.controller.Controller;
import banking.dao.JdbcCardDAO;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandLineParser clp = new CommandLineParser(args);
        String databaseName = "database.db";
        if (clp.getFlag("fileName")) {
            System.out.println("Remember to pass the -fileName argument, for example, -fileName database.db");
            exit(-2);
        }
        databaseName = clp.getArgumentValue("fileName")[0];
        JdbcCardDAO jdbcCardDAO = new JdbcCardDAO(databaseName);
        Controller controller = new Controller(scanner, jdbcCardDAO);
        for (int i = 0; i < 100; i++) {
            controller.run();
        }
        System.out.println("Number of actions exceeded maximum, exiting");
    }
}