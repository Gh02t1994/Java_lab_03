package game.handlers;

import game.command.Command;
import game.engine.GameCond;

public class EndTurnHandler implements IHandlerCommand{
    @Override
    public void execute(Command command, GameCond condition) {

        if (!condition.isStarted()) {
            throw new IllegalStateException("Игра ещё не началась!");
        }

        condition.nextTurn();

        System.out.println("Теперь ход игрока " + condition.getCurrentPlayer());
    }
}
