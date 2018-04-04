package seabattlepack.view;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

    public class Sound {

        private Clip myClip;

    public Sound() {
                try (InputStream is = Sound.class.getResourceAsStream("/audio/pirate.wav");
                     AudioInputStream sound = AudioSystem.getAudioInputStream(new BufferedInputStream(is))){

                     myClip = AudioSystem.getClip();
                     myClip.open(sound);
                 } catch (MalformedURLException e) {
                     throw new RuntimeException("Sound: Malformed URL: " + e);
                 } catch (UnsupportedAudioFileException e) {
                     throw new RuntimeException("Sound: Unsupported Audio File: " + e);
                 } catch (IOException e) {
                     throw new RuntimeException("Sound: Input/Output Error: " + e);
                 } catch (LineUnavailableException e) {
                     e.printStackTrace();
                 }
           }

    public void play() {
       myClip.setFramePosition(0);  // Must always rewind!
        myClip.loop(-1);
        myClip.start();
    }

    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        myClip.stop();
    }

}