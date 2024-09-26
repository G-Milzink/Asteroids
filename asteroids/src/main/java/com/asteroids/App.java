package com.asteroids;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application {

    private static Scene view;
    private static Pane  pane;

    @Override
    public void start(Stage window) throws Exception {
        //Setup main view:
        pane = new Pane();
        view = new Scene(pane);






        //Assign view to window and show window:
        window.setScene(view);
        window.show();
    }



    public static void main(String[] args) {
        launch();
    }

}