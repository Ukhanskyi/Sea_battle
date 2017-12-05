package seabattlepack;

import javafx.scene.media.AudioClip;

import java.util.Timer;

public class Audio {
    AudioClip sound = new AudioClip(this.getClass().getResource("/audio/pirate.wav").toExternalForm());

    public void play() {
        sound.play();
    }

    public void stop() {
        sound.stop();
    }

    public void music_Return(){
        Timer tim = new Timer();
    }
}


