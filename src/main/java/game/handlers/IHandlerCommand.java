package game.handlers;

import game.command.Command;
import game.engine.GameCond;

public interface IHandlerCommand {
    void execute(Command command, GameCond condition);
}
