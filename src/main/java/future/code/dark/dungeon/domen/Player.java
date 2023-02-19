package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import javax.swing.*;
import java.awt.*;

import static future.code.dark.dungeon.config.Configuration.*;

public class Player extends DynamicObject {
    private static final Image landImage = new ImageIcon(LAND_SPRITE).getImage();
    private static final Image winImage = new ImageIcon(WIN_SPRITE).getImage();
    private static final int stepSize = 1;
    private boolean win = false;
    private boolean[] coinsHider = new boolean[10];

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);


    }

    public void drawCoins(Graphics graphics) {
        int tmpXPosition = getXPosition();
        int tmpYPosition = getYPosition();
        if (tmpXPosition == 12 && tmpYPosition == 10) coinsHider[0] = true;

        if (tmpXPosition == 6 && tmpYPosition == 10) coinsHider[1] = true;

        if (tmpXPosition == 1 && tmpYPosition == 10) coinsHider[2] = true;

        if (tmpXPosition == 13 && tmpYPosition == 7) coinsHider[3] = true;

        if (tmpXPosition == 7 && tmpYPosition == 6) coinsHider[4] = true;

        if (tmpXPosition == 1 && tmpYPosition == 7) coinsHider[5] = true;

        if (tmpXPosition == 16 && tmpYPosition == 1) coinsHider[6] = true;

        if (tmpXPosition == 9 && tmpYPosition == 1) coinsHider[7] = true;

        if (tmpXPosition == 1 && tmpYPosition == 1) coinsHider[8] = true;

        if (tmpXPosition == 6 && tmpYPosition == 2) {
            win = true;
        }

        if (win) {
            graphics.drawImage(winImage, 3 * SPRITE_SIZE, -1 * SPRITE_SIZE, null);
            for (int i = 0; i < coinsHider.length; i++) coinsHider[i] = false;
        }

        if (coinsHider[0]) graphics.drawImage(landImage, 12 * SPRITE_SIZE, 10 * SPRITE_SIZE, null);
        if (coinsHider[1]) graphics.drawImage(landImage, 6 * SPRITE_SIZE, 10 * SPRITE_SIZE, null);
        if (coinsHider[2]) graphics.drawImage(landImage, 1 * SPRITE_SIZE, 10 * SPRITE_SIZE, null);
        if (coinsHider[3]) graphics.drawImage(landImage, 13 * SPRITE_SIZE, 7 * SPRITE_SIZE, null);
        if (coinsHider[4]) graphics.drawImage(landImage, 7 * SPRITE_SIZE, 6 * SPRITE_SIZE, null);
        if (coinsHider[5]) graphics.drawImage(landImage, 1 * SPRITE_SIZE, 7 * SPRITE_SIZE, null);
        if (coinsHider[6]) graphics.drawImage(landImage, 16 * SPRITE_SIZE, 1 * SPRITE_SIZE, null);
        if (coinsHider[7]) graphics.drawImage(landImage, 9 * SPRITE_SIZE, 1 * SPRITE_SIZE, null);
        if (coinsHider[8]) graphics.drawImage(landImage, 1 * SPRITE_SIZE, 1 * SPRITE_SIZE, null);
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}" + "    Collected coins: " + coinsCollected + "   Uncollected coins: " + coinsUncollected;
    }
}
