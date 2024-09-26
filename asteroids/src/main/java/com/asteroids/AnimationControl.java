package com.asteroids;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class AnimationControl {

    private Map<KeyCode, Boolean> pressedKeys;
    private Scene gameWorld;
    private Ship player;


    public AnimationControl(Scene gameWorld, Ship player) {
        this.pressedKeys = new HashMap<>();
        this.gameWorld = gameWorld;
        this.player = player;

        this.gameWorld.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        this.gameWorld.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
    }

    public boolean isKeyPressed(KeyCode key) {
        return pressedKeys.getOrDefault(key, false);
    }

    



}
