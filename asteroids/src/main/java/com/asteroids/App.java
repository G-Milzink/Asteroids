package com.asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene gameWorld;
    private static Pane canvas;
    public static int screenWidth = 800;
    public static int screenHeight = 600;
    private static int initialNrOfAsteroids = 8;
    private static Text scoreBoard = new Text(20, 40, "Score: 0");
    private static Font gameFont = new Font(STYLESHEET_MODENA, 40);
    private static AtomicInteger score = new AtomicInteger();
    private static SimpleTimer bulletTimer = new SimpleTimer(0.3);
    private static AudioSystem audioSystem = new AudioSystem();
    private static Image spaceImage = new Image("file:asteroids/src/main/java/com/asteroids/img/space.jpg");
    private static ImageView space = new ImageView(spaceImage);

    List<Bullet> bullets = new ArrayList<>();
    Ship player = new Ship(screenWidth / 2, screenHeight / 2);

    @Override
    public void start(Stage window) throws Exception {
        // Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(screenWidth, screenHeight);
        canvas.setStyle("-fx-background-color: black;");

        scoreBoard.setFont(gameFont);
        scoreBoard.setFill(Color.WHITE);
        gameWorld = new Scene(canvas);

        // Initialize an asteroid:
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < initialNrOfAsteroids; i++) {
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(screenWidth / 3), rnd.nextInt(screenHeight));
            asteroids.add(asteroid);
        }

        // Initialize animationControl:
        AnimationControl animationControl = new AnimationControl(gameWorld);

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
                
                if (animationControl.isKeyPressed(KeyCode.LEFT)) {
                    player.turnLeft();
                }

                if (animationControl.isKeyPressed(KeyCode.RIGHT)) {
                    player.turnRight();
                }

                if (animationControl.isKeyPressed(KeyCode.UP)) {
                    player.accelerate();
                }

                if (animationControl.isKeyPressed(KeyCode.SPACE)) {
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

                // Check for collisions:
                // Ship collision:
                asteroids.forEach(asteroid -> {
                    if (player.collide(asteroid)) {
                        audioSystem.playerDeathSound();
                        stop();
                    }
                });

                // Bullet Collisions:
                bullets.forEach(bullet -> {
                    asteroids.forEach(asteroid -> {
                        if (bullet.collide(asteroid)) {
                            bullet.setAlive(false);
                            asteroid.setAlive(false);
                        }
                    });

                    if (!bullet.isAlive()) {
                        scoreBoard.setText("Score: " + score.addAndGet(100));
                    }
                });

                bullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .forEach(bullet -> canvas.getChildren().remove(bullet.getEntity()));
                bullets.removeAll(bullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .collect(Collectors.toList()));

                asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .forEach(asteroid -> {
                            canvas.getChildren().remove(asteroid.getEntity());
                            audioSystem.asteroidSound();
                        });
                asteroids.removeAll(asteroids.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .collect(Collectors.toList()));

                if (Math.random() < 0.005) {
                    Asteroid asteroid = new Asteroid(screenWidth, screenHeight);
                    if (!asteroid.collide(player)) {
                        asteroids.add(asteroid);
                        canvas.getChildren().add(asteroid.getEntity());
                    }
                }

            }

        }.start();
    }

    // Handle Bullets:
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

    public static void main(String[] args) {
        launch();
    }

}