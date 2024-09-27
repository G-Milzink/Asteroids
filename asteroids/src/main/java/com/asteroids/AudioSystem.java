package com.asteroids;

import javafx.scene.media.AudioClip;

public class AudioSystem {

    private AudioClip bulletSFX;
    private AudioClip asteroidDeathSFX;
    private AudioClip playerDeathSFX;

    public AudioSystem() {
        this.bulletSFX = new AudioClip(getClass().getResource("/sfx/bullet.wav").toExternalForm());
        this.asteroidDeathSFX = new AudioClip(getClass().getResource("/sfx/explosion.wav").toExternalForm());
        this.playerDeathSFX = new AudioClip(getClass().getResource("/sfx/death.wav").toExternalForm());
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
