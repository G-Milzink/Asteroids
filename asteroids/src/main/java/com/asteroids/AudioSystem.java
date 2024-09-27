package com.asteroids;

import javafx.scene.media.AudioClip;

public class AudioSystem {

    private AudioClip bulletSFX;
    private AudioClip asteroidDeathSFX;
    private AudioClip playerDeathSFX;

    public AudioSystem() {
        this.bulletSFX = new AudioClip("file:asteroids/src/main/java/com/asteroids/sfx/bullet.wav");
        this.asteroidDeathSFX = new AudioClip("file:asteroids/src/main/java/com/asteroids/sfx/explosion.wav");
        this.playerDeathSFX = new AudioClip("file:asteroids/src/main/java/com/asteroids/sfx/death.wav");
    }

    public void bulletSound() {
        bulletSFX.play();
    }

    public void asteroidSound() {
        asteroidDeathSFX.play();
    }

    public void playerDeathSound() {
        playerDeathSFX.play();
    }

}
