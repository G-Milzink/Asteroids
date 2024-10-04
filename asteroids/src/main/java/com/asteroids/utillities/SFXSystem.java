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
    private Clip bossBulletSFX;

    public SFXSystem() {
        try {
            bulletSFX = loadClip("asteroids/src/main/resources/sfx/bullet.wav");
            asteroidDeathSFX = loadClip("asteroids/src/main/resources/sfx/explosion.wav");
            playerDeathSFX = loadClip("asteroids/src/main/resources/sfx/death.wav");
            levelUpSFX = loadClip("asteroids/src/main/resources/sfx/level_up.wav");
            bossHitSFX = loadClip("asteroids/src/main/resources/sfx/boss_hit.wav");
            bossDeathSFX = loadClip("asteroids/src/main/resources/sfx/boss_death.wav");
            bossBulletSFX = loadClip("asteroids/src/main/resources/sfx/boss_shoot.wav");
        } catch (Exception e) {
            System.out.println("Error loading sound clips: " + e.getMessage());
        }
    }

    private Clip loadClip(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File soundFile = new File(filePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return clip;
    }

    private void playSound(final Clip clip) {
        new Thread(() -> {
            if (clip != null) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
            }
        }).start();
    }

    public void bulletSound() {
        playSound(bulletSFX);
    }

    public void asteroidSound() {
        playSound(asteroidDeathSFX);
    }

    public void playerDeathSound() {
        playSound(playerDeathSFX);
    }

    public void levelUpSound() {
        playSound(levelUpSFX);
    }

    public void bossHitSound() {
        playSound(bossHitSFX);
    }

    public void bossDeathSound() {
        playSound(bossDeathSFX);
    }

    public void bossBulletSound() {
        playSound(bossBulletSFX);
    }
}
