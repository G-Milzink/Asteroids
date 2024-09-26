package com.asteroids;

public class Asteroid extends Entity {

    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);
    }

}
