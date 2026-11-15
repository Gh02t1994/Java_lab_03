package game.handlers;

import game.command.Command;
import game.engine.GameCond;

public class StartGameHandler implements IHandlerCommand {
    @Override
    public void execute(Command command, GameCond condition) {
        if (condition.isStarted()) {
            throw new IllegalStateException("Игра уже началась");
        }

        if (condition.getMap() == null) {
            throw new IllegalStateException("Сначала необходимо создать карту");
        }

        condition.setStarted(true);

        System.out.println("Игра начата");
    }
}
