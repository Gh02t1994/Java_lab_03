package game.handlers;

import game.Map;
import game.command.Command;
import game.engine.GameCond;

public class InitMapHandler implements IHandlerCommand {
    @Override
    public void execute(Command command, GameCond condition) {
        String[] arguments = command.getArguments();

        int x = Integer.parseInt(arguments[0]);
        int y = Integer.parseInt(arguments[1]);

        if (x < 1 || y < 1) {
            throw new IllegalArgumentException("Размер карты не может быть отрицательным");
        }

        if (condition.getMap() != null) {
            throw new IllegalStateException("Карта уже создана");
        }

        Map map = new Map(x, y);
        condition.setMap(map);

        System.out.println("Карта успешно создана");
    }
}
