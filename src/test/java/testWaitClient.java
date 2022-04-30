import com.brioal.frames.WaitClient;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.*;

/* This is the GUI test file for frame waitClient.
It should return start the game in local host if you click the start from localhost button
and will return to the previous welcome page if you click the return button.
*/
public class testWaitClient {
    protected FrameFixture window;
    @BeforeAll
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp(){
        WaitClient waitClient = GuiActionRunner.execute(new GuiQuery<WaitClient>() {
            @Override
            protected WaitClient executeInEDT() throws Throwable {
                return new WaitClient(9000,-1);
            }
        });
        window = new FrameFixture(waitClient);
        window.show();
    }

    @Test
    public void testLocalHost(){
        window.button(JButtonMatcher.withText("Start from localhost")).click();
    }

    @Test
    public void testReturn(){
        window.button(JButtonMatcher.withText("Return")).click();

    }

    @AfterEach
    public void tearDown(){
        window.cleanUp();
    }
}
