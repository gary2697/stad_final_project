import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* This is the GUI test file for frame SelectButton.
It should test if the select button change state on click.
*/
public class testSelectButton {
    protected FrameFixture window;
    @BeforeAll
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp(){
        SelectionButtonFrame buttonFrame = GuiActionRunner.execute(new GuiQuery<SelectionButtonFrame>() {
            @Override
            protected SelectionButtonFrame executeInEDT() throws Throwable {
//                SelectionButtonFrame testFrame = new SelectionButtonFrame();
//                testFrame.setVisible(true);
                return new SelectionButtonFrame();
            }
        });
        window = new FrameFixture(buttonFrame);
        window.show();
    }

    @Test
    public void testStateChangeWhenClicking(){
        //window.textBox().requireText("normal");
        window.button().click();
        window.textBox().requireText("normal");
    }
    @AfterEach
    public void tearDown(){
        window.cleanUp();
    }


}
