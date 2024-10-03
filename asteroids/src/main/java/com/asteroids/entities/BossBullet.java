package com.asteroids.entities;

import com.asteroids.App;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BossBullet extends Entity {

    public BossBullet(int x, int y) {
        super(new Polygon(5, -5, 5, 5, -5, 5, -5, -5), x, y);
        this.getEntity().setFill(Color.RED);
    }

    @Override
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.getEntity().getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getEntity().getRotate()));

        changeX *= 0.025;
        changeY *= 0.025;

        this.movement = this.movement.add(changeX, changeY);
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
