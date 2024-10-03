package com.asteroids;

// Java:
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.asteroids.entities.Asteroid;
import com.asteroids.entities.BossBullet;
import com.asteroids.entities.BossCreature1;
import com.asteroids.entities.BossCreature2;
import com.asteroids.entities.Bullet;
import com.asteroids.entities.Ship;
import com.asteroids.utillities.InputLogger;
import com.asteroids.utillities.SFXSystem;
import com.asteroids.utillities.SimpleTimer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene gameWorld;
    private static StackPane canvasWrapper;
    private static Pane canvas;
    public static int screenWidth = 800;
    public static int screenHeight = 600;

    private static final AtomicInteger score = new AtomicInteger();
    private static final Text scoreBoard = new Text(20, 40, "Score: 0");
    private static final Font gameFont = new Font(STYLESHEET_MODENA, 40);

    private static final SimpleTimer bulletTimer = new SimpleTimer(0.2);
    private static final SimpleTimer boss1BulletTimer = new SimpleTimer(0.5);

    private static final SFXSystem audioSystem = new SFXSystem();

    private static boolean canSpawnAsteroids = true;
    private static final int INITIAL_NR_OF_ASTEROIDS = 10;
    private static double asteroidSpawnInterval = 0.005;
    private static final double ASTEROID_SPAWNRATE_INCREASE = 0.005;
    private static int asteroidBaseSpeed = 10;
    private static final int ASTEROID_BASESPEED_INCREMENT = 5;

    private static final int LEVEL_THRESHOLD = 1000;
    private static int bossLevel = 0;
    private static final int[] BOSS_TRIGGER_VALUE = {3000, 5000, 7000};

    BossCreature1 boss1 = null;
    BossCreature2 boss2 = null;

    Ship player = new Ship(screenWidth / 2, screenHeight / 2);
    Boolean playerDied = false;
    List<Asteroid> asteroids = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<BossBullet> bossBullets = new ArrayList<>();

    private static final Image spaceImage = new Image("file:asteroids/src/main/java/com/asteroids/img/space.jpg");
    private static final ImageView space = new ImageView(spaceImage);

    @Override
    public void start(Stage window) throws Exception {

        // Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(screenWidth, screenHeight);
        canvas.setStyle("-fx-background-color: black;");

        scoreBoard.setFont(gameFont);
        scoreBoard.setFill(Color.WHITE);
        canvasWrapper = new StackPane();

        canvasWrapper.getChildren().add(canvas);

        gameWorld = new Scene(canvasWrapper);

        // Initialize asteroids:
        initializeAsteroids(INITIAL_NR_OF_ASTEROIDS);

        // Initialize inputLogger:
        InputLogger inputLogger = new InputLogger(gameWorld);

        // Add entities to canvas:
        canvas.getChildren().add(space);
        canvas.getChildren().add(scoreBoard);
        canvas.getChildren().add(player.getEntity());
        asteroids.forEach(asteroid -> canvas.getChildren().add(asteroid.getEntity()));

        // Assign view to window and show window:
        window.setScene(gameWorld);
        window.show();

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (inputLogger.isKeyPressed(KeyCode.LEFT)) {
                    player.turnLeft();
                }

                if (inputLogger.isKeyPressed(KeyCode.RIGHT)) {
                    player.turnRight();
                }

                if (inputLogger.isKeyPressed(KeyCode.UP)) {
                    player.accelerate();
                }

                if (inputLogger.isKeyPressed(KeyCode.SPACE) && bullets.size() <= 10) {
                    if (bulletTimer.hasTimedOut()) {
                        fireBullet();
                        bulletTimer.reset();
                    }
                }
                bulletTimer.increaseCount();

                // Execute all movement:
                player.move();
                asteroids.forEach(asteroid -> asteroid.move());
                bullets.forEach(bullet -> bullet.move());
                bossBullets.forEach(bullet -> bullet.move());

                // Handle bosses:
                if (boss1 != null) {
                    handleBoss1();
                    if (playerDied) {
                        stop();
                    }
                }

                if (boss2 != null) {
                    handleBoss2();
                    if (playerDied) {
                        stop();
                    }
                }

                // Check for collisions:
                // Ship<->Asteroid:
                asteroids.forEach(asteroid -> {
                    if (player.collide(asteroid)) {
                        audioSystem.playerDeathSound();
                        stop();
                    }
                });

                // Bullet<->Asteroid:
                bullets.forEach(bullet -> {
                    asteroids.forEach(asteroid -> {
                        if (bullet.collide(asteroid)) {
                            bullet.setAlive(false);
                            asteroid.setAlive(false);
                            scoreBoard.setText("Score: " + score.addAndGet(100));
                            checkLevel();
                        }
                    });
                });

                // Player <-> BossBullet
                bossBullets.forEach(bullet -> {
                    if (bullet.collide(player)) {
                        audioSystem.playerDeathSound();
                        stop();
                    }
                });


                // remove "dead" bullets...
                bullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .forEach(bullet -> canvas.getChildren().remove(bullet.getEntity()));

                bullets.removeAll(bullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .collect(Collectors.toList()));
                // remove "dead" asteroids...
                asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .forEach(asteroid -> {
                            canvas.getChildren().remove(asteroid.getEntity());
                            audioSystem.asteroidSound();
                        });

                asteroids.removeAll(asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .collect(Collectors.toList()));

                //Continuously spawn new asteroids:
                if (canSpawnAsteroids) {
                    if (Math.random() < asteroidSpawnInterval) {
                        Asteroid asteroid = new Asteroid(screenWidth, screenHeight, asteroidBaseSpeed);

                        if (!asteroid.collide(player)) {
                            asteroids.add(asteroid);
                            canvas.getChildren().add(asteroid.getEntity());
                        }
                    }
                }

            }

        }.start();
    }

    // Handle spawning of initial asteroids:
    public void initializeAsteroids(int amount) {
        System.out.println("ASTEROIDS!");
        this.asteroids.clear();
        for (int i = 0; i < amount; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(screenWidth / 3), rnd.nextInt(screenHeight),
                    asteroidBaseSpeed);
            this.asteroids.add(asteroid);
        }
    }

    // Handle firing bullets:
    public void fireBullet() {
        audioSystem.bulletSound();
        Bullet bullet = new Bullet((int) player.getEntity().getTranslateX(),
                (int) player.getEntity().getTranslateY());

        bullet.getEntity().setRotate(player.getEntity().getRotate());
        bullets.add(bullet);

        bullet.accelerate();
        bullet.setMovement(bullet.getMovement().normalize().multiply(3));

        canvas.getChildren().add(bullet.getEntity());
    }

    // Handle firing Bossbullets:
    public void fireBossBullet1() {
        
        audioSystem.bulletSound();
        BossBullet bullet = new BossBullet((int) boss1.getEntity().getTranslateX(),
                (int) boss1.getEntity().getTranslateY());

        bullet.getEntity().setRotate(boss1.getEntity().getRotate());
        bossBullets.add(bullet);

        bullet.accelerate();
        bullet.setMovement(bullet.getMovement().normalize().multiply(10));

        canvas.getChildren().add(bullet.getEntity());
    }

    //Check level status:
    public void checkLevel() {
        if (score.get() > 0 && score.get() % LEVEL_THRESHOLD == 0) {
            audioSystem.levelUpSound();
            asteroidSpawnInterval += ASTEROID_SPAWNRATE_INCREASE;
            asteroidBaseSpeed += ASTEROID_BASESPEED_INCREMENT;
            bossCheck(score.get());
        }
    }

    public int[] pickSpawnLocation() {
        Random rnd = new Random();
        int choice = rnd.nextInt(4);
        int[] result = new int[2];
        switch (choice) {
            case 0:
                result[0] = screenWidth / 2;
                result[1] = 75;
                break;
            case 1:
                result[0] = screenWidth / 2;
                result[1] = screenHeight - 75;
                break;
            case 2:
                result[0] = 75;
                result[1] = screenHeight / 2;
                break;
            case 3:
                result[0] = screenWidth - 75;
                result[1] = screenHeight / 2;
                break;
            default:
                break;
        }

        return result;
    }

    //Check if boss needs to spawn and do so if needed:
    public void bossCheck(int score) {
        if (score == BOSS_TRIGGER_VALUE[0] && bossLevel == 0) {
            bossLevel++;
            for (Asteroid asteroid : asteroids) {
                asteroid.setAlive(false);
                canSpawnAsteroids = false;
            }
            int[] spawnXY = pickSpawnLocation();
            boss1 = new BossCreature1(spawnXY[0], spawnXY[1]);
            canvas.getChildren().add(boss1.getEntity());
        }
        if (score == BOSS_TRIGGER_VALUE[1] && bossLevel == 1) {
            bossLevel++;
            for (Asteroid asteroid : asteroids) {
                asteroid.setAlive(false);
                canSpawnAsteroids = false;
            }
            int[] spawnXY = pickSpawnLocation();
            boss2 = new BossCreature2(spawnXY[0], spawnXY[1]);
            canvas.getChildren().add(boss2.getEntity());
        }
        if (score == BOSS_TRIGGER_VALUE[2] && bossLevel == 2) {
            bossLevel++;
            System.out.println("Spawn Boss: " + bossLevel);
        }
    }

    //handle boss 1:
    public void handleBoss1() {
        boss1.move();
        if (boss1BulletTimer.hasTimedOut()) {
            fireBossBullet1();
            boss1BulletTimer.reset();
        }
        boss1BulletTimer.increaseCount();

        bullets.forEach(bullet -> {
            if (boss1.collide(bullet)) {
                bullet.setAlive(false);
                boss1.decreaseHitpoints();
                audioSystem.bossHitSound();
            }
        });

        if (boss1.collide(player)) {
            audioSystem.playerDeathSound();
            playerDied = true;
        }

        if (boss1.getHitpoints() <= 0) {
            audioSystem.bossDeathSound();
            audioSystem.asteroidSound();
            canvas.getChildren().remove(boss1.getEntity());
            canSpawnAsteroids = true;
            boss1 = null;
        }
    }

    //handle boss2:
    public void handleBoss2() {
        boss2.move();
        bullets.forEach(bullet -> {
            if (boss2.collide(bullet)) {
                bullet.setAlive(false);
                boss2.decreaseHitpoints();
                audioSystem.bossHitSound();
            }
        });

        if (boss2.collide(player)) {
            audioSystem.playerDeathSound();
            playerDied = true;
        }

        if (boss2.getHitpoints() <= 0) {
            audioSystem.bossDeathSound();
            audioSystem.asteroidSound();
            canvas.getChildren().remove(boss2.getEntity());
            canSpawnAsteroids = true;
            boss2 = null;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
