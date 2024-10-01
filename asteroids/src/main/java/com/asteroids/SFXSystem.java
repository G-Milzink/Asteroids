package com.asteroids;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class SFXSystem {

    private Clip bulletSFX;
    private Clip asteroidDeathSFX;
    private Clip playerDeathSFX;

    public SFXSystem() {
        try {
            File bulletSoundFile = new File("asteroids/src/main/resources/sfx/bullet.wav");
            AudioInputStream bulletStream = AudioSystem.getAudioInputStream(bulletSoundFile);
            this.bulletSFX = AudioSystem.getClip();
            this.bulletSFX.open(bulletStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            File asteroidSoundFile = new File("asteroids/src/main/resources/sfx/explosion.wav");
            AudioInputStream asteroidStream = AudioSystem.getAudioInputStream(asteroidSoundFile);
            this.asteroidDeathSFX = AudioSystem.getClip();
            this.asteroidDeathSFX.open(asteroidStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            File deathSoundFile = new File("asteroids/src/main/resources/sfx/death.wav");
            AudioInputStream deathStream = AudioSystem.getAudioInputStream(deathSoundFile);
            this.playerDeathSFX = AudioSystem.getClip();
            this.playerDeathSFX.open(deathStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void bulletSound() {
        if (bulletSFX != null) {
            bulletSFX.stop();
            bulletSFX.setFramePosition(0);
            bulletSFX.start();
        }
    }

    public void asteroidSound() {
        if (asteroidDeathSFX != null) {
            asteroidDeathSFX.stop();
            asteroidDeathSFX.setFramePosition(0);
            asteroidDeathSFX.start();
        }
    }

    public void playerDeathSound() {
        if (playerDeathSFX != null) {
            playerDeathSFX.stop();
            playerDeathSFX.setFramePosition(0);
            playerDeathSFX.start();
        }
    }

}
