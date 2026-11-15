package game.engine;

import game.buildings.Building;
import game.subsribers.EventManager;
import game.Map;
import game.units.Unit;

import java.util.Arrays;

public class GameCond {
    private boolean started;

    private Map map;
    private Unit[] units;
    private Building[] buildings;
    private EventManager eventManager;
    private int currentPlayer = 1;

    public GameCond() {
        this.started = false;
        this.units = new Unit[0];
        this.buildings = new Building[0];
        this.eventManager = new EventManager();
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Unit[] getUnits() {
        return units;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Unit findUnit(String id) {
        for (Unit unit : units)
            if (unit.getId().equals(id))
                return unit;

        return null;
    }

    public Building findBuilding(String id) {
        for (Building building : buildings)
            if (building.getId().equals(id))
                return building;

        return null;
    }

    public void add_Unit(Unit unit) {
        units = Arrays.copyOf(units, units.length + 1);
        units[units.length - 1] = unit;
    }

    public boolean has_Unit(String id) {
        return findUnit(id) != null;
    }

    public void add_building(Building building) {
        buildings = Arrays.copyOf(buildings, buildings.length + 1);
        buildings[buildings.length - 1] = building;
    }

    public boolean has_Building(String id) {
        return findBuilding(id) != null;
    }

    public void damage_Unit(String id, int damage) {
        Unit unit = findUnit(id);

        if (unit == null) {
            throw new IllegalArgumentException("Юнит не найден");
        }

        unit.setHealth(unit.getHealth() - damage);
    }

    public void nextTurn() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        }
        else {
            currentPlayer = 1;
        }
    }
}
