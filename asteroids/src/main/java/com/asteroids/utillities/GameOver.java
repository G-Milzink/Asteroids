package com.asteroids.utillities;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameOver {

    private BorderPane canvas;
    private int screenWidth = 800;
    private int screenHeight = 600;
    

    public GameOver() {
        this.canvas = new BorderPane();
        this.canvas.setPrefSize(screenWidth, screenHeight);
        this.canvas.setStyle("-fx-background-color: black;");
    }

    public Scene gameOverScreen(int points) {        
        Button button = new Button("EXIT");

        Text text = new Text("GAME OVER!");
        text.setFill(Color.WHITE);

        Text score = new Text("SCORE: " + points);
        score.setFill(Color.WHITE);
        

        VBox layout = new VBox(25);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(text);
        layout.getChildren().add(score);
        layout.getChildren().add(button);

        canvas.setCenter(layout);

        button.setOnMouseClicked(event -> {
            System.exit(0);
        });

        Scene scene = new Scene(canvas);

        return scene;
    }

}
