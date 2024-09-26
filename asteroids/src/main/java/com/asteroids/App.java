package com.asteroids;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene gameWorld;
    private static Pane canvas;
    public static int screenWidth = 800;
    public static int screenHeight = 600;
    private static int initialNrOfAsteroids = 8;

    List<Bullet> bullets = new ArrayList<>();
    Ship player = new Ship(screenWidth / 2, screenHeight / 2);

    @Override
    public void start(Stage window) throws Exception {
        // Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(screenWidth, screenHeight);
        canvas.setStyle("-fx-background-color: white;");

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

                if (animationControl.isKeyPressed(KeyCode.SPACE) && bullets.size()<3) {
                    fireBullet();
                }
                //Execute all movement:
                player.move();
                asteroids.forEach(asteroid -> asteroid.move());
                bullets.forEach(bullet -> bullet.move());


                asteroids.forEach(asteroid -> {
                    if (player.collide(asteroid)) {
                        stop();
                    }
                });
            }

        }.start();
    }

    // Handle Bullets:
    public void fireBullet() {
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