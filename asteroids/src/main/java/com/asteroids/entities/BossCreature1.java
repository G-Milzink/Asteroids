package com.asteroids.entities;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class BossCreature1 extends Entity {

    private static final int SIZE = 40;
    private static final Polygon body = new Polygon(-SIZE, -SIZE, SIZE, -SIZE, SIZE, SIZE, -SIZE, SIZE);
    private final double rotationalMovement;
    private int hitpoints = 10;

    private final Image image = new Image("file:asteroids/src/main/java/com/asteroids/img/metal_texture.png");
    private final ImagePattern texture = new ImagePattern(image);

    public BossCreature1(int x, int y) {
        super(body, x, y);
        this.getEntity().setFill(texture);
        this.getEntity().setStroke(Color.GREEN);
        this.getEntity().setStrokeWidth(1.0);
        super.getEntity().setTranslateX(x);
        super.getEntity().setTranslateY(y);

        Random rng = new Random();
        int selector = rng.nextInt(2);
        if (selector == 0) {
            rotationalMovement = -1.25;
        } else {
            rotationalMovement = 1.25;
        }

    }

    @Override
    public void move() {
        super.getEntity().setRotate(super.getEntity().getRotate() + rotationalMovement);
        accelerate();
        super.move();
    }

    public void decreaseHitpoints() {
        this.hitpoints--;
    }

    public int getHitpoints() {
        return this.hitpoints;
    }

}
