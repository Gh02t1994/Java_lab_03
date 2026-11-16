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

    public Set<String> unigueUnitId() {
        return commands.stream()
                .filter(command -> command.getName().equals("addUnit"))
                .map(command -> command.getArguments()[0])
                .collect(
                        () -> new HashSet<String>(),
                        (set, id) -> set.add(id),
                        (set1, set2) -> set1.addAll(set2)
                );
    }

    public Map<String, Long> countAllCommands() {
        return commands.stream()
                .collect(Collectors.groupingBy(
                        Command::getName,
                        Collectors.counting()
                ))
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

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
