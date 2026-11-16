package game.executer;

import game.command.Command;
import game.engine.IGameEngine;
import game.script.GameScript;

public class Executer {
    private IGameEngine engine;

    public Executer(IGameEngine engine) {
        this.engine = engine;
    }

    public void run(GameScript script) {
        for (Command command : script) {
            try {
                engine.exec(command);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
