package com.asteroids;

import java.util.Random;

import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;

public class Asteroid extends Entity {

    private double rotationalMovement;
    private Image texture = new Image("file:asteroids/src/main/java/com/asteroids/img/asteroid_texture.jpg");
    private ImagePattern imagePattern = new ImagePattern(texture);

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);
        this.getEntity().setFill(imagePattern);

        Random rnd = new Random();

        super.getEntity().setRotate(rnd.nextInt(360));

        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - rnd.nextDouble();

    }

    @Override
    public void move() {
        super.move();
        super.getEntity().setRotate(super.getEntity().getRotate()+rotationalMovement);
    }

}
