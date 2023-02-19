package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;

import javax.swing.*;
import java.awt.*;


import static future.code.dark.dungeon.config.Configuration.*;

public class Coin extends GameObject{

    private static final Image exitImage = new ImageIcon(EXIT_SPRITE).getImage();
    public Coin(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.COIN_SPRITE);
    }

}
