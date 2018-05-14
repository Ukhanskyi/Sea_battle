package seabattlepack.view;

import org.junit.Before;

public class SoundTest {

    Sound sound ;

    @Before
    public void setSound(){
        sound = new Sound();
    }

   /* @Test(expected = IOException.class)
    public void testCreateTempFile() throws IOException {
        Path tmpDir = Files.createTempDirectory("audio");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "pirate", ".wav");
    }*/

}