package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(scanner);
        for (int i = 0; i < 100; i++) {
            controller.run();
        }
        System.out.println("Number of actions exceeded maximum, exiting");
    }
}