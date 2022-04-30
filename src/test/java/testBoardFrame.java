import com.brioal.frames.BoradFrame;
import com.brioal.panels.PlayerPanel;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;
import java.awt.*;

/* This is the GUI test file for frame BoardFrame and PlayerPanel which support the main rendering of the game.
It should put a stone on the frame as the mouse clicking.
*/

public class testBoardFrame {
    protected FrameFixture window;
    @BeforeAll
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp(){
        BoradFrame welcome = GuiActionRunner.execute(new GuiQuery<BoradFrame>() {
            @Override
            protected BoradFrame executeInEDT() throws Throwable {
                PlayerPanel panel = new PlayerPanel("localhost",9000,  -1);
                return new BoradFrame(panel, 8, -1);
            }
        });
        window = new FrameFixture(welcome);
        window.resizeTo(new Dimension(740,740));
        window.show();
    }

    @Test
    public void testBoardFrameStart(){
        window.click();
        window.resizeTo(new Dimension(740,740));
        window.click();
        window.resizeTo(new Dimension(740,740));

    }


    @AfterEach
    public void tearDown(){
        window.cleanUp();
    }

}
