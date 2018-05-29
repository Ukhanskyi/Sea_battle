package seabattlepack.view;

import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Clip;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SoundTest {

    Sound sound ;
    private Clip myClip;

    @Before
    public void setSound(){
        sound = new Sound();
    }

    @Test(expected = IOException.class)
    public void testCreateTempFile() throws IOException {
        Path tmpDir = Files.createTempDirectory("audio");
        tmpDir.toFile().delete();

    }
}