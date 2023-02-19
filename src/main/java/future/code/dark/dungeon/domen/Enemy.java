package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

public class Enemy extends DynamicObject{
    private static final int stepSize = 1;
    public Enemy(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.GHOST_SPRITE);
    }
    public void move(Direction direction) {
        super.move(direction, stepSize);
    }
}
