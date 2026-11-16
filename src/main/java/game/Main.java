package game;

import game.command.Command;
import game.engine.GameEngine;
import game.executer.Executer;
import game.script.GameScript;
import game.subsribers.ConsoleLog;

public class Main {
    public static void main(String[] args) {
        GameScript script = new GameScript();
        GameEngine engine = new GameEngine();
        Executer exec = new Executer(engine);

        ConsoleLog logger = new ConsoleLog();
        engine.getCondition().getEventManager().subscribe(logger);

        script.add(new Command("initMap", "10", "10"));
        script.add(new Command("addUnit", "unit1", "warrior", "1", "1"));
        script.add(new Command("addUnit", "unit2", "archer", "2", "2"));
        script.add(new Command("addUnit", "unit3", "warrior", "3", "3"));
        script.add(new Command("startGame"));
        script.add(new Command("build", "build1", "barracks", "7", "7"));
        script.add(new Command("move", "unit1", "4", "4"));
        script.add(new Command("print"));

        System.out.println("Команда, встречающаяся наиболее часто:\n" + script.mostPopularCommand());

        System.out.println('\n' + "Уникальные идентификаторы юнитов:");
        script.unigueUnitId().forEach(x -> System.out.println(x));

        System.out.println('\n' + "Список команд, упорядоченный по популярности:");
        script.countAllCommands().forEach((key, value) -> System.out.println(key + " - " + value));

        String name_command = "attack";
        System.out.println('\n' + "Встречается ли команда " + name_command + ":");
        String flag = script.containsCommandType(name_command) ? "Да" : "Нет";
        System.out.println(flag);

        System.out.println('\n' + "Список сущностей, сгрупированный по количеству:");
        script.countAllUnits().forEach((key, value) -> System.out.println(key + " - " + value));
        System.out.println();

        exec.run(script);
    }
}
