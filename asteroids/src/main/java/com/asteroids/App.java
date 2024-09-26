package com.asteroids;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene gameWorld;
    private static Pane canvas;
    private static int screenWidth = 800;
    private static int screenHeight = 600;

    @Override
    public void start(Stage window) throws Exception {
        // Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(screenWidth, screenHeight);
        canvas.setStyle("-fx-background-color: white;");
        gameWorld = new Scene(canvas);
        // Initialize player:
        Ship player = new Ship(screenWidth / 2, screenHeight / 2);
        // Initialize an asteroid:
        Asteroid asteroid = new Asteroid(50, 50);
        asteroid.turnRight();
        asteroid.turnRight();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        asteroid.accelerate();
        // Initialize animationControl:
        AnimationControl animationControl = new AnimationControl(gameWorld);

        canvas.getChildren().add(player.getEntity());
        canvas.getChildren().add(asteroid.getEntity());

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

                player.move();
                asteroid.move();

                if (player.collide(asteroid)) {
                    stop();
                }
            }

        }.start();
    }

    public static void main(String[] args) {
        launch();
    }

}