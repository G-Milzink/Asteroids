package com.asteroids;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Asteroid extends Entity {

    private double rotationalMovement;
    private Image image = new Image("file:asteroids/src/main/java/com/asteroids/img/asteroid_texture.jpg");
    private ImagePattern texture = new ImagePattern(image);

    private int baseSpeed;

    public Asteroid(int x, int y, int baseSpeed) {
        super(new PolygonFactory().createPolygon(), x, y);
        this.getEntity().setFill(texture);
        this.getEntity().setStroke(Color.GREY);
        this.getEntity().setStrokeWidth(1.0);
        this.baseSpeed = baseSpeed;

        Random rnd = new Random();

        super.getEntity().setRotate(rnd.nextInt(360));

        int accelerationAmount = this.baseSpeed + rnd.nextInt(25);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - rnd.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getEntity().setRotate(super.getEntity().getRotate() + rotationalMovement);
    }

}
