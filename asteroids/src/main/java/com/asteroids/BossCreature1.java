package com.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BossCreature1 extends Entity {

    private static int size = 40;
    private static Polygon body = new Polygon(-size, -size, size, -size, size, size, -size, size);
    private double rotationalMovement = 2.5;

    public BossCreature1(int x, int y) {
        super(body, x, y);
        super.getEntity().setFill(Color.BLACK);
        this.getEntity().setStroke(Color.RED);
        this.getEntity().setStrokeWidth(1.0);
        super.getEntity().setTranslateX(x);
        super.getEntity().setTranslateY(y);

        for (int i = 0; i < 10; i++) {
            accelerate();
        }
    }

    @Override
    public void move() {
        super.move();
        super.getEntity().setRotate(super.getEntity().getRotate() + rotationalMovement);
    }

}
