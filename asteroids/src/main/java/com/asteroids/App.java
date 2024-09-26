package com.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application {

    private static Scene view;

    @Override
    public void start(Stage window) throws Exception {
        Pane pane = new Pane();
        view = new Scene(pane);
        window.setScene(view);
        window.show();
    }



    public static void main(String[] args) {
        launch();
    }

}