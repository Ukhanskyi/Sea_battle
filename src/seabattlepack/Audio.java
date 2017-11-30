package seabattlepack;

import javafx.scene.media.AudioClip;

public class Audio {
    AudioClip sound = new AudioClip(this.getClass().getResource("/sound/Bounce.wav").toExternalForm());

    public void play() {
        sound.play();
    }
    public void stop() { sound.stop(); }
}


