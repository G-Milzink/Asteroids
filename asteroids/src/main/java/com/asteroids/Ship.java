package com.asteroids;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class Ship extends Entity {

    private final Image image = new Image("file:asteroids/src/main/java/com/asteroids/img/ship_texture.png");
    private final ImagePattern texture = new ImagePattern(image);

    public Ship(int x, int y) {
        super(new Polygon(-15, -15, 30, 0, -15, 15), x, y);
        this.getEntity().setFill(texture);
        this.getEntity().setStroke(Color.LIGHTSLATEGRAY);
        this.getEntity().setStrokeWidth(1.0);
    }

}
