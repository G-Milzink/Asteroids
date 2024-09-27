package com.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Entity {


    public Ship(int x, int y) {
        super(new Polygon(-15,-15,30,0,-15,15), x, y);
        this.getEntity().setFill(Color.LIGHTSLATEGRAY);
    }

}
