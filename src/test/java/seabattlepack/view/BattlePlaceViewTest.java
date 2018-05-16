package seabattlepack.view;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BattlePlaceViewTest {

    BattlePlaceView battlePlaceView;
    private JFrame me;
    //Robot robot = BasicRobot.robotWithNewAwtHierarchy();


    @Before
    public void getBattlePlaceView() {
        battlePlaceView = new BattlePlaceView(me);
    }


    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_sea() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "sea", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_ship() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "ship", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_killed() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "killed", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_miss() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "miss", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_border() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "border", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_audio() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "audio", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_noaudio() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "noaudio", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_hint() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "hint", ".png");
    }

    @Test(expected = IOException.class)
    public void BattlePlaceView_should_check_in_Directory_file_background() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "background", ".png");
    }
    
}