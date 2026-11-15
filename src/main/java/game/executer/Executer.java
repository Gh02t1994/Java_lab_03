package game.executer;

import game.command.Command;
import game.engine.IGameEngine;

public class Executer {
    private IGameEngine engine;

    public Executer(IGameEngine engine) {
        this.engine = engine;
    }

    public void run(Command[] commands) {
        for (Command command : commands) {
            engine.exec(command);
        }
    }
}
