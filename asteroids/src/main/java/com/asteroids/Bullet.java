package com.asteroids;

import javafx.scene.shape.Polygon;

public class Bullet extends Entity {

    public Bullet(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
    }

}
