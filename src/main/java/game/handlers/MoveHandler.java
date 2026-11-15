package game.handlers;

import game.units.Unit;
import game.command.Command;
import game.engine.GameCond;

public class MoveHandler implements IHandlerCommand{
    @Override
    public void execute(Command command, GameCond condition){
        String[] arguments = command.getArguments();

        String unId = arguments[0];
        int x = Integer.parseInt(arguments[1]);
        int y = Integer.parseInt(arguments[2]);
        Unit unit = condition.findUnit(unId);

        if (!condition.isStarted()) {
            throw new IllegalStateException("Ошибка: игра ещё не начата");
        }

        if (condition.getMap() == null) {
            throw new IllegalStateException("Ошибка: карта не инициализирована");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Юнит не найден");
        }

        if (unit.getHealth() <= 0) {
            throw new IllegalStateException("Юнит мертв");
        }

        if (!condition.getMap().isInside(x, y)) {
            throw new IllegalArgumentException("Координаты за пределами карты");
        }

        if (!condition.getMap().isFree(x, y)) {
            throw new IllegalStateException("Клетка занята");
        }

        condition.getMap().move_Unit(unit, x, y);

        System.out.println("Перемещен "  + unId + " в " + x + " " + y);
    }
}
