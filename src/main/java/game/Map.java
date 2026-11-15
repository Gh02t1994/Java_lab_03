package game;

import game.units.Unit;

public class Map {
    int width;
    int height;
    GameObject[][] grid;

    public Map(int x, int y){
        this.width = x;
        this.height = y;
        this.grid = new GameObject[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInside(int x, int y) {
        return x > 0 && x <= width && y > 0 && y <= height;
    }

    public boolean isFree(int x, int y) {
        return grid[y - 1][x - 1] == null;
    }

    public void move_Unit(Unit unit, int new_X, int new_Y) {
        grid[unit.getY() - 1][unit.getX() - 1] = null;

        unit.setX(new_X);
        unit.setY(new_Y);

        grid[new_Y - 1][new_X - 1] = unit;
    }

    public void add_Object(GameObject object) {
        int x = object.getX();
        int y = object.getY();

        if (!isInside(x, y))
            throw new IllegalArgumentException("Координаты за пределами карты");

        if (!isFree(x, y))
            throw new IllegalStateException("Клетка занята");

        grid[y - 1][x - 1] = object;
    }
}
