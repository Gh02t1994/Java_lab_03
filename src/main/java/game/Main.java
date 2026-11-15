package game;

import game.command.Command;
import game.command.Parser;
import game.engine.GameEngine;
import game.subsribers.ConsoleLog;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEngine engine = new GameEngine();
        Parser parser = new Parser();

        ConsoleLog logger = new ConsoleLog();
        engine.getCondition().getEventManager().subscribe(logger);

        while (true) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Выход из игры");
                break;
            }

            try {
                Command command = parser.parse(line);
                engine.exec(command);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
