package game;

public abstract class GameObject {
    protected String id;
    protected int x;
    protected int y;
    protected int health;

    public GameObject(String id, int x, int y, int health) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

        if (this.health < 0) {
            this.health = 0;
        }
    }
}
