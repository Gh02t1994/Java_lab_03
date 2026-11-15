package game.handlers;

import game.*;
import game.buildings.Barracks;
import game.buildings.Building;
import game.command.Command;
import game.engine.GameCond;
import game.units.Archer;
import game.units.Unit;
import game.units.Warrior;

public class PrintHandler implements IHandlerCommand {

    @Override
    public void execute(Command command, GameCond condition) {

        if (condition.getMap() == null) {
            throw new IllegalStateException("Карта не инициализирована");
        }

        Map map = condition.getMap();

        System.out.println("Состояние игры:");

        System.out.println("Карта: " + map.getWidth() + " x " + map.getHeight());

        System.out.println("\nЮниты:");

        Unit[] units = condition.getUnits();

        if (units.length == 0) {
            System.out.println("Юнитов нет");
        } else {
            for (Unit unit : units) {
                String type;

                if (unit instanceof Warrior) {
                    type = "Warrior";
                } else if (unit instanceof Archer) {
                    type = "Archer";
                } else {
                    type = "Unknown";
                }

                System.out.println("\n" + type + " (ID: " + unit.getId() + ")");
                System.out.println("Координаты: " + unit.getX() + ", " + unit.getY());
                System.out.println("Здоровье: " + unit.getHealth());
            }
        }

        System.out.println("\nЗдания:");

        Building[] buildings = condition.getBuildings();

        if (buildings.length == 0) {
            System.out.println("Зданий нет");
        } else {
            for (Building building : buildings) {
                String type;

                if (building instanceof Barracks) {
                    type = "Barracks";
                } else {
                    type = "Unknown";
                }

                System.out.println("\n" + type + " (ID: " + building.getId() + ")");
                System.out.println("Координаты: " + building.getX() + ", " + building.getY());
            }
        }
    }
}
