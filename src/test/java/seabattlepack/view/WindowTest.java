/*
package seabattlepack.view;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WindowTest extends AssertJSwingJUnitTestCase {

    private FrameFixture windows;

    @Override
    protected void onSetUp() {
        Window frame = GuiActionRunner.execute(() -> new Window());
        windows = new FrameFixture(robot(), frame);
        //windows.show();
    }

    @Test
    public void shouldFindTitle(){
        windows.requireTitle(" Sea Battle ");
        windows.requireVisible();
        //System.setProperty("java.awt.headless", "true");
        //Toolkit tk = Toolkit.getDefaultToolkit();
    }

   */
/* @Test
    public void shouldBeVisible(){
        windows.requireVisible();
        //System.setProperty("java.awt.headless", "true");
    }*//*


    static {
        System.setProperty("java.awt.headless", "true");
    }

 @Test(expected = IOException.class)
    public void testCreateTempFile() throws IOException {
        Path tmpDir = Files.createTempDirectory("img");
        tmpDir.toFile().delete();
        Path tmpFile = Files.createTempFile(tmpDir, "icon1", ".png");
    }

}
*/
