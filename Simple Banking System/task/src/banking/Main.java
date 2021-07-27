package banking;

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
        System.out.println(databaseName);
        Database database = new Database(databaseName);
        Controller controller = new Controller(scanner, database);
        for (int i = 0; i < 100; i++) {
            controller.run();
        }
        System.out.println("Number of actions exceeded maximum, exiting");
    }
}