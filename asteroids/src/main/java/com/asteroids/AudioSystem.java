package com.asteroids;

import javafx.scene.media.AudioClip;

public class AudioSystem {

    private AudioClip bulletSFX;
    private AudioClip asteroidDeathSFX;
    private AudioClip playerDeathSFX;

    public AudioSystem() {
        try {
            this.bulletSFX = new AudioClip(getClass().getResource("/com/asteroids/sfx/bullet.wav").toString());
            this.asteroidDeathSFX = new AudioClip(getClass().getResource("/com/asteroids/sfx/explosion.wav").toString());
            this.playerDeathSFX = new AudioClip(getClass().getResource("/com/asteroids/sfx/death.wav").toString());
        } catch (Exception e) {
            System.err.println("Error loading audio files: " + e.getMessage());
        }
    }

    public void bulletSound() {
        bulletSFX.stop();
        bulletSFX.play();
    }

    public void asteroidSound() {
        asteroidDeathSFX.stop();
        asteroidDeathSFX.play();
    }

    public void playerDeathSound() {
        playerDeathSFX.stop();
        playerDeathSFX.play();
    }

}
