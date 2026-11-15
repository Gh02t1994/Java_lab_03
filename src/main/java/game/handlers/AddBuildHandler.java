package game.handlers;

import game.buildings.Barracks;
import game.buildings.Building;
import game.command.Command;
import game.engine.GameCond;

public class AddBuildHandler implements IHandlerCommand {
    @Override
    public void execute(Command command, GameCond condition) {
        String[] arguments = command.getArguments();

        String buId = arguments[0];
        String type = arguments[1];
        int x = Integer.parseInt(arguments[2]);
        int y = Integer.parseInt(arguments[3]);

        if (condition.getMap() == null) {
            throw new IllegalStateException("Ошибка: карта не инициализирована");
        }

        if (condition.has_Building(buId)) {
            throw new IllegalStateException("Здание с таким id уже существует");
        }

        if (!condition.getMap().isInside(x, y)) {
            throw new IllegalArgumentException("Координаты за пределами карты");
        }

        if (!condition.getMap().isFree(x, y)) {
            throw new IllegalStateException("Клетка занята");
        }

        Building building;

        switch (type) {
            case "barracks":
                building = new Barracks(buId, x, y, 100);
                break;

            default:
                throw new IllegalArgumentException("Несуществующий тип здания");
        }

        condition.getMap().add_Object(building);
        condition.add_building(building);

        condition.getEventManager().Sent_notify(
                "Построено здание " + buId
        );
    }
}
