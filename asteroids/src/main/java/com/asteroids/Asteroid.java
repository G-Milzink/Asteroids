package com.asteroids;

import java.util.Random;

public class Asteroid extends Entity {

    private double rotationalMovement;

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

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
