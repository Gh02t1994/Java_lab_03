package game.script;

import game.command.Command;

import java.util.*;
import java.util.stream.Collectors;

public class GameScript implements Iterable<Command>{
    private List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public String mostPopularCommand() {
        Map<String, Long> mapCommands =
                commands.stream()
                        .collect(Collectors.groupingBy(Command::getName, Collectors.counting()));

        Map.Entry<String, Long> popular_com =
                mapCommands.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue()).orElse(null);

        if (popular_com == null) {
            return null;
        }
        else {
            return popular_com.getKey();
        }
    }

    public List<String> unigueUnitId() {
        return commands.stream()
                .filter(command -> command.getName().equals("addUnit"))
                .map(command -> command.getArguments()[0])
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Long> countAllCommands() {
        return commands.stream()
                .collect(Collectors.groupingBy(Command::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public boolean containsCommandType(String command_name) {
        return commands.stream()
                .anyMatch(command -> command.getName().equals(command_name));
    }

    public Map<String, Long> countAllUnits() {
        return commands.stream()
                .filter(command -> command.getName().equals("addUnit"))
                .collect(Collectors.groupingBy(command -> command.getArguments()[1], Collectors.counting()));
    }

    public List<String> unitsInArea(int x_min, int x_max) {
        return commands.stream()
                .filter(command -> command.getName().equals("addUnit"))
                .filter(command -> Integer.parseInt(command.getArguments()[2]) >= x_min)
                .filter(command -> Integer.parseInt(command.getArguments()[2]) <= x_max)
                .map(command -> command.getArguments()[0])
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
