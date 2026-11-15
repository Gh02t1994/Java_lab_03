package game.units;

import game.GameObject;

public abstract class Unit extends GameObject {
    protected int damage;

    public Unit(String id, int x, int y, int damage, int health) {
        super(id, x, y, health);

        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
