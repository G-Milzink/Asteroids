package com.asteroids.entities;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class BossCreature2 extends Entity {

    private static final int poly_w = 10;
    private static final int poly_l = 60;
    private static final Polygon body = new Polygon(
        poly_w, -poly_l, poly_w, -poly_w, poly_l, -poly_w, poly_l, poly_w, poly_w, poly_w, poly_w, poly_l,
            -poly_w, poly_l, -poly_w, poly_w, -poly_l, poly_w, -poly_l, -poly_w, -poly_w, -poly_w, -poly_w, -poly_l);
    private final double rotationalMovement;
    private  int hitpoints = 20;

    private final Image image = new Image("file:asteroids/src/main/java/com/asteroids/img/metal_texture.png");
    private final ImagePattern texture = new ImagePattern(image);

    public BossCreature2(int x, int y, Color color) {
        super(body, x, y);
        this.getEntity().setFill(texture);
        this.getEntity().setStroke(color);
        this.getEntity().setStrokeWidth(1.25);
        super.getEntity().setTranslateX(x);
        super.getEntity().setTranslateY(y);

        Random rng = new Random();
        int selector = rng.nextInt(2);
        if (selector == 0) {
            rotationalMovement = -.5;
        } else {
            rotationalMovement = .5;
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
