package game.buildings;

import game.GameObject;

public abstract class Building extends GameObject {
    public Building(String id, int x, int y, int health) {
        super(id, x, y, health);
    }
}
