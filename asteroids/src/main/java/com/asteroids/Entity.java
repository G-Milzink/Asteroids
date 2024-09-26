package com.asteroids;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

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
        this.entity.setTranslateX(this.entity.getTranslateX() + this.movement.getX());
        this.entity.setTranslateY(this.entity.getTranslateY() + this.movement.getY());

        if (this.entity.getTranslateX() < 0) {
            this.entity.setTranslateX(this.entity.getTranslateX() + App.screenWidth);
        }

        if (this.entity.getTranslateX() > App.screenWidth) {
            this.entity.setTranslateX(this.entity.getTranslateX() % App.screenWidth);
        }

        if (this.entity.getTranslateY() < 0) {
            this.entity.setTranslateY(this.entity.getTranslateY() + App.screenHeight);
        }

        if (this.entity.getTranslateY() > App.screenHeight) {
            this.entity.setTranslateY(this.entity.getTranslateY() % App.screenHeight);
        }
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(this.entity.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.entity.getRotate()));

        changeX *= 0.025;
        changeY *= 0.025;

        this.movement = this.movement.add(changeX, changeY);
    }

    public boolean collide(Entity other) {
        Shape collisionArea = Shape.intersect(this.entity, other.getEntity());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

}
