import com.brioal.frames.WelcomeFrame;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

import java.awt.*;

/* This is the GUI test file for frame WelcomeFrame.
It should move to the ConnectService frame if you click start
and should change state if you click on the buttons.
*/
public class testWelcomeFrame {

    protected FrameFixture window;
    @BeforeAll
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp(){
        WelcomeFrame welcome = GuiActionRunner.execute(new GuiQuery<WelcomeFrame>() {
            @Override
            protected WelcomeFrame executeInEDT() throws Throwable {
                return new WelcomeFrame();
            }
        });
        window = new FrameFixture(welcome);
        window.resizeTo(new Dimension(740,740));
        window.show();
    }

    @Test
    public void testFrameStart(){
        window.button(JButtonMatcher.withText("Start")).click();
    }

    @Test
    public void testButtonSelected(){
        window.button(JButtonMatcher.withText("Server")).click();
        window.button(JButtonMatcher.withText("White")).click();
    }

    @AfterEach
    public void tearDown(){
        window.cleanUp();
    }



}
