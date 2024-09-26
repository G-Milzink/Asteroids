package com.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application {

    private static Scene view;
    private static Pane  canvas;
    private static int screenWidth = 800;
    private static int screenHeight = 600;

    @Override
    public void start(Stage window) throws Exception {
        //Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(screenWidth, screenHeight);
        canvas.setStyle("-fx-background-color: white;");
        //Initialize player:
        Ship player = new Ship(screenWidth/2, screenHeight/2);
        //Initialize an asteroid:
        Asteroid asteroid = new Asteroid(50, 50);



        canvas.getChildren().add(player.getEntity());
        canvas.getChildren().add(asteroid.getEntity());





        //Assign view to window and show window:
        view = new Scene(canvas);
        window.setScene(view);
        window.show();
    }



    public static void main(String[] args) {
        launch();
    }

}