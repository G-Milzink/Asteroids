package com.asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Entity {

    private Polygon entity;
    private Point2D movement;

    public Entity(Polygon polygon, int x, int y) {
        this.entity = polygon;
        this.entity.setTranslateX(x);
        this.entity.setTranslateY(y);

        this.movement = new Point2D(0, 0);
    }

    public Polygon getEntity() {
        return entity;
    }

    public void turnLeft() {
        this.entity.setRotate(this.entity.getRotate() - 5);
    }

    public void turnRight() {
        this.entity.setRotate(this.entity.getRotate() + 5);
    }

    public void move() {
        this.entity.setTranslateX(this.entity.getTranslateX()+this.movement.getX());
        this.entity.setTranslateY(this.entity.getTranslateY()+this.movement.getY());
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.entity.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.entity.getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.movement = this.movement.add(changeX,changeY);
    }

}
