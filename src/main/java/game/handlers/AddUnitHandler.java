package game.handlers;

import game.units.Archer;
import game.units.Unit;
import game.units.Warrior;
import game.command.Command;
import game.engine.GameCond;

public class AddUnitHandler implements IHandlerCommand {
    @Override
    public void execute(Command command, GameCond condition){
        String[] arguments = command.getArguments();

        String unId = arguments[0];
        String type = arguments[1];
        int x = Integer.parseInt(arguments[2]);
        int y = Integer.parseInt(arguments[3]);

        if (condition.getMap() == null) {
            throw new IllegalStateException("Ошибка: карта не инициализирована");
        }

        if (condition.isStarted()) {
            throw new IllegalStateException("Игра уже началась");
        }

        if (condition.has_Unit(unId)) {
            throw new IllegalStateException("Юнит с таким id уже существует");
        }

        if (!condition.getMap().isInside(x, y)) {
            throw new IllegalArgumentException("Координаты за пределами карты");
        }

        if (!condition.getMap().isFree(x, y)) {
            throw new IllegalStateException("Клетка занята");
        }

        Unit unit;

        switch (type) {
            case "warrior":
                unit = new Warrior(unId, x, y, 20, 100);
                break;

            case "archer":
                unit = new Archer(unId, x, y, 30, 80);
                break;

            default:
                throw new IllegalArgumentException("Несуществующий тип юнита");
        }

        condition.getMap().add_Object(unit);
        condition.add_Unit(unit);

        System.out.println("Добавлен " + type + " (" + unId + ") в " + x + " " + y);
    }
}
