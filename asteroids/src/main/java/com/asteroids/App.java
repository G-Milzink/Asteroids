package com.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application {

    private static Scene view;
    private static Pane  canvas;

    @Override
    public void start(Stage window) throws Exception {
        //Setup main view:
        canvas = new Pane();
        canvas.setPrefSize(800, 600);
        canvas.setStyle("-fx-background-color: black;");
        view = new Scene(canvas);






        //Assign view to window and show window:
        window.setScene(view);
        window.show();
    }



    public static void main(String[] args) {
        launch();
    }

}