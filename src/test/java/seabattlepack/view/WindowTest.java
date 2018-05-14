package seabattlepack.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WindowTest {
    Window window;

    @Before
    public void getWindow() {
        window = new Window();
    }

    @Test(expected = IOException.class)
    public void testCreateTempFile() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "icon1", ".png");
    }
}