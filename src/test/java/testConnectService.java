import com.brioal.frames.ConnectService;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* This is the GUI test file for frame ConnectService.
It should return start the game in server mode if you click the start button
and will return to the previous welcome page if you click the return button.
*/
public class testConnectService {
    protected FrameFixture window;
    @BeforeAll
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp(){
        ConnectService connectService = GuiActionRunner.execute(new GuiQuery<ConnectService>() {
            @Override
            protected ConnectService executeInEDT() throws Throwable {
                return new ConnectService(1);
            }
        });
        window = new FrameFixture(connectService);
        window.show();
    }

    @Test
    public void testConnectStart(){
        window.button(JButtonMatcher.withText("Start")).click();
    }

    @Test
    public void testConnectCancel(){
        window.button(JButtonMatcher.withText("Cancel")).click();
    }

    @AfterEach
    public void tearDown(){
        window.cleanUp();
    }

}
