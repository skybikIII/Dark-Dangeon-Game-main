package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;
import static future.code.dark.dungeon.config.Configuration.*;

public abstract class DynamicObject extends GameObject {
    boolean[] collectedUncollectedCoins = new boolean[10];
    public boolean allCoinsCollected = false;

    public DynamicObject(int xPosition, int yPosition, String imagePath) {
        super(xPosition, yPosition, imagePath);
        for (int i = 0; i < 10; i++) {
            collectedUncollectedCoins[i] = false;
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected void move(Direction direction, int distance) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();
        int tmpCoinsCollected = getCoinsCollected();
        int tmpCoinsUncollected = getCoinsUncollected();
        switch (direction) {
            case UP -> tmpYPosition -= distance;
            case DOWN -> tmpYPosition += distance;
            case LEFT -> tmpXPosition -= distance;
            case RIGHT -> tmpXPosition += distance;
        }

        if (isAllowedSurface(tmpXPosition, tmpYPosition)) {
            if (isAllowedExit(tmpXPosition, tmpYPosition) || (allCoinsCollected)) {
                xPosition = tmpXPosition;
                yPosition = tmpYPosition;
            }
        }
        if (!collectedUncollectedCoins[0]) {
            if (tmpXPosition == 12 && tmpYPosition == 10) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[0] = true;
            }
        }
        if (!collectedUncollectedCoins[1]) {
            if (tmpXPosition == 6 && tmpYPosition == 10) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[1] = true;
            }
        }
        if (!collectedUncollectedCoins[2]) {
            if (tmpXPosition == 1 && tmpYPosition == 10) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[2] = true;
            }
        }
        if (!collectedUncollectedCoins[3]) {
            if (tmpXPosition == 13 && tmpYPosition == 7) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;

                collectedUncollectedCoins[3] = true;
            }
        }
        if (!collectedUncollectedCoins[4]) {
            if (tmpXPosition == 7 && tmpYPosition == 6) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[4] = true;
            }
        }
        if (!collectedUncollectedCoins[5]) {
            if (tmpXPosition == 1 && tmpYPosition == 7) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[5] = true;
            }
        }
        if (!collectedUncollectedCoins[6]) {
            if (tmpXPosition == 16 && tmpYPosition == 1) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;

                collectedUncollectedCoins[6] = true;
            }
        }
        if (!collectedUncollectedCoins[7]) {
            if (tmpXPosition == 9 && tmpYPosition == 1) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;

                collectedUncollectedCoins[7] = true;
            }
        }
        if (!collectedUncollectedCoins[8]){
            if (tmpXPosition == 1 && tmpYPosition == 1) {
                tmpCoinsCollected++;
                tmpCoinsUncollected--;
                collectedUncollectedCoins[8] = true;
            }
        }
        coinsCollected = tmpCoinsCollected;
        coinsUncollected = tmpCoinsUncollected;
        if (coinsCollected == 9) allCoinsCollected = true;
    }


    private Boolean isAllowedSurface(int x, int y) {
        return GameMaster.getInstance().getMap().getMap()[y][x] != Configuration.WALL_CHARACTER; //для 3 пункта
    }
    private Boolean isAllowedExit(int x, int y) {
        return GameMaster.getInstance().getMap().getMap()[y][x] != EXIT_CHARACTER; //для 3 пункта
    }
}