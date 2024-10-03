package com.asteroids.utillities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SFXSystem {

    private Clip bulletSFX;
    private Clip asteroidDeathSFX;
    private Clip playerDeathSFX;
    private Clip levelUpSFX;
    private Clip bossHitSFX;
    private Clip bossDeathSFX;

    public SFXSystem() {
        try {
            File bulletSoundFile = new File("asteroids/src/main/resources/sfx/bullet.wav");
            AudioInputStream bulletStream = AudioSystem.getAudioInputStream(bulletSoundFile);
            this.bulletSFX = AudioSystem.getClip();
            this.bulletSFX.open(bulletStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
        try {
            File asteroidSoundFile = new File("asteroids/src/main/resources/sfx/explosion.wav");
            AudioInputStream asteroidStream = AudioSystem.getAudioInputStream(asteroidSoundFile);
            this.asteroidDeathSFX = AudioSystem.getClip();
            this.asteroidDeathSFX.open(asteroidStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
        try {
            File deathSoundFile = new File("asteroids/src/main/resources/sfx/death.wav");
            AudioInputStream deathStream = AudioSystem.getAudioInputStream(deathSoundFile);
            this.playerDeathSFX = AudioSystem.getClip();
            this.playerDeathSFX.open(deathStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
        try {
            File levelUpSoundFile = new File("asteroids/src/main/resources/sfx/level_up.wav");
            AudioInputStream levelUpStream = AudioSystem.getAudioInputStream(levelUpSoundFile);
            this.levelUpSFX = AudioSystem.getClip();
            this.levelUpSFX.open(levelUpStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }

        try {
            File bossHitSoundFile = new File("asteroids/src/main/resources/sfx/boss_hit.wav");
            AudioInputStream bossHitStream = AudioSystem.getAudioInputStream(bossHitSoundFile);
            this.bossHitSFX = AudioSystem.getClip();
            this.bossHitSFX.open(bossHitStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }

        try {
            File bossDeathSoundFile = new File("asteroids/src/main/resources/sfx/boss_death.wav");
            AudioInputStream bossDeathStream = AudioSystem.getAudioInputStream(bossDeathSoundFile);
            this.bossDeathSFX = AudioSystem.getClip();
            this.bossDeathSFX.open(bossDeathStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
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

    public void levelUpSound() {
        if (levelUpSFX != null) {
            levelUpSFX.stop();
            levelUpSFX.setFramePosition(0);
            levelUpSFX.start();
        }
    }

    public void bossHitSound() {
        if (bossHitSFX != null) {
            bossHitSFX.stop();
            bossHitSFX.setFramePosition(0);
            bossHitSFX.start();
        }
    }

    public void bossDeathSound() {
        if (bossDeathSFX != null) {
            bossDeathSFX.stop();
            bossDeathSFX.setFramePosition(0);
            bossDeathSFX.start();
        }
    }

}
