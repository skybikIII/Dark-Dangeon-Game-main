package future.code.dark.dungeon.service;
import future.code.dark.dungeon.config.Configuration;

import future.code.dark.dungeon.domen.Coin;
import future.code.dark.dungeon.domen.DynamicObject;
import future.code.dark.dungeon.domen.Enemy;
import future.code.dark.dungeon.domen.Exit;
import future.code.dark.dungeon.domen.GameObject;
import future.code.dark.dungeon.domen.Map;
import future.code.dark.dungeon.domen.Player;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static future.code.dark.dungeon.config.Configuration.*;

public class GameMaster implements ActionListener {
    Timer timer = new Timer(500, this);
    private static GameMaster instance;
    private final Map map;
    public final List<GameObject> gameObjects;
    boolean gameOver = false;
    private static final Image gameOverImage = new ImageIcon(GAME_OVER_SPRITE).getImage();

    public static synchronized GameMaster getInstance() {
        if (instance == null) {
            instance = new GameMaster();
        }
        return instance;
    }

    private GameMaster() {
        try {
            this.map = new Map(Configuration.MAP_FILE_PATH);
            this.gameObjects = initGameObjects(map.getMap());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GameObject> initGameObjects(char[][] map) {
        List<GameObject> gameObjects = new ArrayList<>();
        Consumer<GameObject> addGameObject = gameObjects::add;
        Consumer<Enemy> addEnemy = enemy -> {
            if (ENEMIES_ACTIVE) gameObjects.add(enemy);
        };
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case EXIT_CHARACTER -> addGameObject.accept(new Exit(j, i));
                    case COIN_CHARACTER -> addGameObject.accept(new Coin(j, i));
                    case ENEMY_CHARACTER -> addEnemy.accept(new Enemy(j, i));
                    case PLAYER_CHARACTER -> addGameObject.accept(new Player(j, i));

                }

            }
        }
        return gameObjects;
    }


    public void renderFrame(Graphics graphics) {
        timer.start();
        getMap().render(graphics);
        getStaticObjects().forEach(gameObject -> gameObject.render(graphics));
        getEnemies().forEach(gameObject -> gameObject.render(graphics));
        getPlayer().drawCoins(graphics);
        getEnemy().render(graphics);
        getPlayer().render(graphics);
        graphics.setColor(Color.WHITE);
        graphics.drawString(getPlayer().toString(), 10, 20);
        for (int i = 0; i < 2; i++) {
            if ((getEnemies().get(i).getXPosition() == getPlayer().getXPosition()) &&
                    (getEnemies().get(i).getYPosition() == getPlayer().getYPosition())) gameOver = true;
        }
        if (gameOver) graphics.drawImage(gameOverImage, 4 * SPRITE_SIZE, 2 * SPRITE_SIZE, null);
    }


    public Player getPlayer() {
        return (Player) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Player)
                .findFirst()
                .orElseThrow();
    }

    public Enemy getEnemy() {
        return (Enemy) gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .findFirst()
                .orElseThrow();
    }

    private List<GameObject> getStaticObjects() {
        return gameObjects.stream()
                .filter(gameObject -> !(gameObject instanceof DynamicObject))
                .collect(Collectors.toList());
    }

    private List<Enemy> getEnemies() {
        return gameObjects.stream()
                .filter(gameObject -> gameObject instanceof Enemy)
                .map(gameObject -> (Enemy) gameObject)
                .collect(Collectors.toList());
    }

    public Map getMap() {
        return map;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 2; i++) {
            if (getEnemies().get(i).getYPosition() < getPlayer().getYPosition())
                getEnemies().get(i).move(DynamicObject.Direction.DOWN);
            if (getEnemies().get(i).getYPosition() > getPlayer().getYPosition())
                getEnemies().get(i).move(DynamicObject.Direction.UP);
            if (getEnemies().get(i).getXPosition() < getPlayer().getXPosition())
                getEnemies().get(i).move(DynamicObject.Direction.RIGHT);
            if (getEnemies().get(i).getXPosition() > getPlayer().getXPosition())
                getEnemies().get(i).move(DynamicObject.Direction.LEFT);
        }
    }
}
