package game.engine;

import game.command.Command;

public interface IGameEngine {
    void exec(Command command);
}
