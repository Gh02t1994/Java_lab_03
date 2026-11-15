package game.engine;

import game.command.Command;
import game.handlers.*;

import java.util.HashMap;
import java.util.Map;

public class GameEngine implements IGameEngine {
    private GameCond condition;
    private Map<String, IHandlerCommand> handlers;

    public GameEngine() {
        condition = new GameCond();

        handlers = new HashMap<>();

        handlers.put("addUnit", new AddUnitHandler());
        handlers.put("build", new AddBuildHandler());
        handlers.put("move", new MoveHandler());
        handlers.put("attack", new AttackHandler());
        handlers.put("startGame", new StartGameHandler());
        handlers.put("initMap", new InitMapHandler());
        handlers.put("print", new PrintHandler());
        handlers.put("endTurn", new EndTurnHandler());
    }

    public GameCond getCondition() {
        return condition;
    }

    @Override
    public void exec(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("Команда не задана");
        }

        IHandlerCommand handler = handlers.get(command.getName());

        if (handler == null) {
            throw new IllegalArgumentException("Неизвестная команда: " + command.getName());
        }

        handler.execute(command, condition);
    }
}
