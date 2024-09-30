package com.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Bullet extends Entity {

    public Bullet(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        this.getEntity().setFill(Color.RED);
    }

    @Override
    public void move() {
        this.getEntity().setTranslateX(this.getEntity().getTranslateX() + this.getMovement().getX());
        this.getEntity().setTranslateY(this.getEntity().getTranslateY() + this.getMovement().getY());

        if (this.getEntity().getTranslateX() < 0) {
            this.setAlive(false);
        }

        if (this.getEntity().getTranslateX() > App.screenWidth) {
            this.setAlive(false);

        }

        if (this.getEntity().getTranslateY() < 0) {
            this.setAlive(false);

        }

        if (this.getEntity().getTranslateY() > App.screenHeight) {
            this.setAlive(false);

        }
    }
}
