package game.handlers;

import game.units.Unit;
import game.command.Command;
import game.engine.GameCond;

public class AttackHandler implements IHandlerCommand {
    @Override
    public void execute(Command command, GameCond condition) {
        String[] arguments = command.getArguments();

        String unId_1 = arguments[0];
        String unId_2 = arguments[1];
        Unit unit_1 = condition.findUnit(unId_1);
        Unit unit_2 = condition.findUnit(unId_2);

        if (!condition.isStarted()) {
            throw new IllegalStateException("Ошибка: игра ещё не начата");
        }

        if (condition.getMap() == null) {
            throw new IllegalStateException("Ошибка: карта не инициализирована");
        }

        if (unId_1.equals(unId_2)) {
            throw new IllegalArgumentException("Юнит не может атаковать сам себя");
        }

        if (unit_1 == null) {
            throw new IllegalArgumentException("Юнит " + unId_1 + " не найден");
        }

        if (unit_2 == null) {
            throw new IllegalArgumentException("Юнит " + unId_2 + " не найден");
        }

        if (unit_1.getHealth() <= 0) {
            throw new IllegalStateException("Юнит " + unId_1 + " мертв");
        }

        if (unit_2.getHealth() <= 0) {
            throw new IllegalStateException("Юнит " + unId_2 + " мертв");
        }

        condition.damage_Unit(unId_2, unit_1.getDamage());

        condition.getEventManager().Sent_notify("Юнит " + unId_1 + " атаковал " + unId_2);

        if (unit_2.getHealth() <= 0) {
            condition.getEventManager().Sent_notify("Юнит " + unId_2 + " погиб");
        }
    }
}
