package tests;
import com.brioal.utool.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.brioal.utool.Calculate;
// we did not get a good score for our mutation testing since all the pingjia method does not alter the output (only change the internal state of the board)
//it will take approaxitely 15 min to generate a pittest report
//one issue with pingjia method changing the board is that it even makes mutation testing very difficult since
//we have no control of our input values, and it becomes extremly difficult to genearte test cases to kill the mutant because of that
public class CalculatePitTest {

    @Test // test add a null point NPE
    public void testAddPointNullPoint()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.addPoint(null);
        });
    }
    @Test // test add a out of bound  point exception
    public void testAddPointOutOfBoundPoint()
    {
        Calculate black = new Calculate(1);
        Point p = new Point(100,100);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,()->{
            black.addPoint(p);
        });
    }
    @Test // test add a black point success
    public void testAddPointBlackPoint()
    {
        Calculate black = new Calculate(1);
        Point p = new Point(1,1,1);
        black.addPoint(p);
        Assertions.assertEquals(1,black.getDatas()[1][1]);
    }
    @Test // test add a white point success
    public void testAddPointWhitePoint()
    {
        Calculate white = new Calculate(-1);
        Point p = new Point(1,1,-1);
        white.addPoint(p);
        Assertions.assertEquals(-1,white.getDatas()[1][1]);
    }
    @Test // add blakc twice
    public void testAddPointAddBlackTwice()
    {
        Calculate black = new Calculate(-1);
        Point p = new Point(1,1,1);
        black.addPoint(p);
        black.addPoint(p);
        Assertions.assertEquals(1,black.getDatas()[1][1]);
    }
    @Test // add blakc twice
    public void testAddPointAddWhiteTwice()
    {
        Calculate white = new Calculate(-1);
        Point p = new Point(1,1,-1);
        white.addPoint(p);
        white.addPoint(p);
        Assertions.assertEquals(-1,white.getDatas()[1][1]);
    }
    @Test // test add a point to a cell that has been occupied should not change the color
    public void testAddPointTwoPointsDifferentColorCheckBlack()
    {
        Calculate black = new Calculate(1);
        Point p = new Point(1,1,1);
        black.addPoint(p);
        Calculate white = new Calculate(-1);
        Point p2 = new Point(1,1,-1);
        white.addPoint(p2);
        Assertions.assertEquals(1,black.getDatas()[1][1]);
    }

    // test judge win
    @Test
    public void testJudgeWinNull()
    {
        Calculate black = new Calculate(-1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.JudegeWin(null);
        });
    }
    @Test // test add a out of bound  point exception
    public void testJudgeWinOutOfBoundPoint()
    {
        Calculate black = new Calculate(1);
        Point p = new Point(100,100);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,()->{
            black.JudegeWin(p);
        });
    }
    //add five points with the same color in a col should return true
    @Test
    public void testJudgeWinFiveColSameColorCheckRightMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p5));
    }
    // test judge win
    //add five points with the same color in a col should return true
    @Test
    public void testJudgeWinFiveColSameColorCheckLeftMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p1));
    }
    @Test
    public void testJudgeWinFiveColSameColorCheckMiddle()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p3));
    }

    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveColDiffColor()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p1));
    }


    //add five points with the same color in a row should return true
    @Test
    public void testJudgeWinFiveRowSameColorCheckRightMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p5));
    }
    // test judge win
    //add five points with the same color in a row should return true
    @Test
    public void testJudgeWinFiveRowSameColorCheckLeftMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p1));
    }
    @Test
    public void testJudgeWinFiveRowSameColorCheckMiddle()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p3));
    }

    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveRowDiffColor()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p3));
    }

    //add five points with the same color in a left diagnol should return true
    @Test
    public void testJudgeWinFiveLeftDiagSameColorCheckRightMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p5));
    }
    // test judge win
    //add five points with the same color in a left diag should return true
    @Test
    public void testJudgeWinFiveLeftDiagSameColorCheckLeftMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p1));
    }
    @Test
    public void testJudgeWinFiveLeftDiagSameColorCheckMiddle()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p3));
    }

    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveLeftDiagDiffColor()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p3));
    }

    //add five points with the same color in a right diagnol should return true
    @Test
    public void testJudgeWinFiveRightDiagSameColorCheckRightMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p5));
    }
    // test judge win
    //add five points with the same color in a right diag should return true
    @Test
    public void testJudgeWinFiveRightDiagSameColorCheckLeftMost()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p1));
    }
    @Test
    public void testJudgeWinFiveRightDiagSameColorCheckMiddle()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(true,black.JudegeWin(p3));
    }

    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveRightDiagDiffColor()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p3));
    }

    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveRightDiagDiffColorRightBorder()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(16,16,1);
        black.addPoint(p2);
        Point p3 = new Point(17,17,-1);
        black.addPoint(p3);
        Point p4 = new Point(18,18,1);
        black.addPoint(p4);
        Point p5 = new Point(19,19,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p5));
    }
    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveRightDiagDiffColorRightBoderX()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(16,2,1);
        black.addPoint(p2);
        Point p3 = new Point(17,3,-1);
        black.addPoint(p3);
        Point p4 = new Point(18,4,1);
        black.addPoint(p4);
        Point p5 = new Point(19,5,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p5));
    }
    @Test //check 5 stones diff color should return false
    public void testJudgeWinFiveRightDiagDiffColorRightBoderY()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,16,1);
        black.addPoint(p2);
        Point p3 = new Point(3,17,-1);
        black.addPoint(p3);
        Point p4 = new Point(4,18,1);
        black.addPoint(p4);
        Point p5 = new Point(5,19,1);
        black.addPoint(p5);
        Assertions.assertEquals(false,black.JudegeWin(p5));
    }
    @Test
    public void testGetDatasNull()
    {
        Calculate black = new Calculate(1);
        Assertions.assertEquals(0,black.getDatas()[0][0]);
    }
    //test pingjia4
    @Test
    public void testPingjia4Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia4(null);
        });
    }
    //test pingjia4
    @Test
    public void testPingjia4RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(4,1,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,1,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,1,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,1,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }

    @Test
    public void testPingjia4RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,1);
        white.addPoint(p3);
        Point p4 = new Point(4,1,1);
        white.addPoint(p4);
        Point p5 = new Point(5,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Point p6= new Point(1,6,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));

    }
    @Test
    public void testPingjia4ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(1,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test //fault detected
    public void testPingjia4ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }

    @Test
    public void testPingjia4ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,1);
        white.addPoint(p3);
        Point p4 = new Point(1,4,1);
        white.addPoint(p4);
        Point p5 = new Point(1,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }

    @Test
    public void testPingjia4LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Point p6= new Point(6,6,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(11,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,1,1);
        black.addPoint(p1);
        Point p2 = new Point(2,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test //fault detected
    public void testPingjia4LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    @Test
    public void testPingjia4LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }

    @Test
    public void testPingjia4LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,1);
        white.addPoint(p3);
        Point p4 = new Point(4,4,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia4(d));
    }
    //test pingjia3
    @Test
    public void testPingjia3Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia3(null);
        });
    }
    //test pingjia3
    @Test
    public void testPingjia3RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(15,2,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        Point p4 = new Point(15,4,1);
        black.addPoint(p4);
        Point p5 = new Point(15,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(15,2,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        Point p4 = new Point(15,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(15,2,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(15,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(15,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }

    @Test
    public void testPingjia3RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,1);
        white.addPoint(p3);
        Point p4 = new Point(15,4,1);
        white.addPoint(p4);
        Point p5 = new Point(15,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,1,1);
        black.addPoint(p2);
        Point p3 = new Point(13,1,1);
        black.addPoint(p3);
        Point p4 = new Point(12,1,1);
        black.addPoint(p4);
        Point p5 = new Point(11,1,1);
        black.addPoint(p5);
        Point p6= new Point(10,1,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
        Assertions.assertEquals(0,black.pingjia3(d));

    }
    @Test
    public void testPingjia3ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,1,1);
        black.addPoint(p2);
        Point p3 = new Point(13,1,1);
        black.addPoint(p3);
        Point p4 = new Point(12,1,1);
        black.addPoint(p4);
        Point p5 = new Point(11,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,1,1);
        black.addPoint(p2);
        Point p3 = new Point(13,1,1);
        black.addPoint(p3);
        Point p4 = new Point(12,1,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,1,1);
        black.addPoint(p2);
        Point p3 = new Point(13,1,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,1,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test //fault detected
    public void testPingjia3ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,1,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,1,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,1,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }

    @Test
    public void testPingjia3ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,1,1);
        white.addPoint(p3);
        Point p4 = new Point(12,1,1);
        white.addPoint(p4);
        Point p5 = new Point(11,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }

    @Test
    public void testPingjia3LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,2,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,4,1);
        black.addPoint(p4);
        Point p5 = new Point(11,5,1);
        black.addPoint(p5);
        Point p6= new Point(10,6,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,2,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,4,1);
        black.addPoint(p4);
        Point p5 = new Point(11,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,2,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,2,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,1,1);
        black.addPoint(p1);
        Point p2 = new Point(14,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test //fault detected
    public void testPingjia3LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }
    @Test
    public void testPingjia3LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }

    @Test
    public void testPingjia3LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,1);
        white.addPoint(p3);
        Point p4 = new Point(12,4,1);
        white.addPoint(p4);
        Point p5 = new Point(11,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia3(d));
    }

    //test pingjia5
    @Test
    public void testPingjia5Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia5(null);
        });
    }
    @Test
    public void testPingjia5RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,15,1);
        black.addPoint(p2);
        Point p3 = new Point(13,15,1);
        black.addPoint(p3);
        Point p4 = new Point(12,15,1);
        black.addPoint(p4);
        Point p5 = new Point(11,15,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,15,1);
        black.addPoint(p2);
        Point p3 = new Point(13,15,1);
        black.addPoint(p3);
        Point p4 = new Point(12,15,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,15,1);
        black.addPoint(p2);
        Point p3 = new Point(13,15,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,15,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,15,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,15,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,15,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,15,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    @Test
    public void testPingjia5RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,15,1);
        white.addPoint(p3);
        Point p4 = new Point(12,15,1);
        white.addPoint(p4);
        Point p5 = new Point(11,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(15,14,1);
        black.addPoint(p2);
        Point p3 = new Point(15,13,1);
        black.addPoint(p3);
        Point p4 = new Point(15,12,1);
        black.addPoint(p4);
        Point p5 = new Point(15,11,1);
        black.addPoint(p5);
        Point p6= new Point(15,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia5(d));

    }
    @Test
    public void testPingjia5ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(15,14,1);
        black.addPoint(p2);
        Point p3 = new Point(15,13,1);
        black.addPoint(p3);
        Point p4 = new Point(15,12,1);
        black.addPoint(p4);
        Point p5 = new Point(15,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(15,14,1);
        black.addPoint(p2);
        Point p3 = new Point(15,13,1);
        black.addPoint(p3);
        Point p4 = new Point(15,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(15,14,1);
        black.addPoint(p2);
        Point p3 = new Point(15,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(15,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test //fault detected
    public void testPingjia5ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(15,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(-1,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    @Test
    public void testPingjia5ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,13,1);
        white.addPoint(p3);
        Point p4 = new Point(15,12,1);
        white.addPoint(p4);
        Point p5 = new Point(15,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    @Test
    public void testPingjia5LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,14,1);
        black.addPoint(p2);
        Point p3 = new Point(13,13,1);
        black.addPoint(p3);
        Point p4 = new Point(12,12,1);
        black.addPoint(p4);
        Point p5 = new Point(11,1,1);
        black.addPoint(p5);
        Point p6= new Point(10,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,14,1);
        black.addPoint(p2);
        Point p3 = new Point(13,13,1);
        black.addPoint(p3);
        Point p4 = new Point(12,12,1);
        black.addPoint(p4);
        Point p5 = new Point(11,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,14,1);
        black.addPoint(p2);
        Point p3 = new Point(13,13,1);
        black.addPoint(p3);
        Point p4 = new Point(12,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,14,1);
        black.addPoint(p2);
        Point p3 = new Point(13,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,15,1);
        black.addPoint(p1);
        Point p2 = new Point(14,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test //fault detected
    public void testPingjia5LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }
    @Test
    public void testPingjia5LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    @Test
    public void testPingjia5LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,13,1);
        white.addPoint(p3);
        Point p4 = new Point(12,12,1);
        white.addPoint(p4);
        Point p5 = new Point(11,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    //test pingjia6
    @Test
    public void testPingjia6Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia6(null);
        });
    }
    @Test
    public void testPingjia6RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(1,14,1);
        black.addPoint(p2);
        Point p3 = new Point(1,13,1);
        black.addPoint(p3);
        Point p4 = new Point(1,12,1);
        black.addPoint(p4);
        Point p5 = new Point(1,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(1,14,1);
        black.addPoint(p2);
        Point p3 = new Point(1,13,1);
        black.addPoint(p3);
        Point p4 = new Point(1,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(1,14,1);
        black.addPoint(p2);
        Point p3 = new Point(1,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(1,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    @Test
    public void testPingjia6RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,13,1);
        white.addPoint(p3);
        Point p4 = new Point(1,12,1);
        white.addPoint(p4);
        Point p5 = new Point(1,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(4,15,1);
        black.addPoint(p4);
        Point p5 = new Point(5,15,1);
        black.addPoint(p5);
        Point p6= new Point(6,15,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));

    }
    @Test
    public void testPingjia6ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(4,15,1);
        black.addPoint(p4);
        Point p5 = new Point(5,15,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(4,15,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,15,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test //fault detected
    public void testPingjia6ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,15,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,15,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,15,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,15,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    @Test
    public void testPingjia6ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,1);
        white.addPoint(p3);
        Point p4 = new Point(4,15,1);
        white.addPoint(p4);
        Point p5 = new Point(5,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    @Test
    public void testPingjia6LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(4,12,1);
        black.addPoint(p4);
        Point p5 = new Point(5,11,1);
        black.addPoint(p5);
        Point p6= new Point(6,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(4,12,1);
        black.addPoint(p4);
        Point p5 = new Point(5,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(4,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,15,1);
        black.addPoint(p1);
        Point p2 = new Point(2,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test //fault detected
    public void testPingjia6LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }
    @Test
    public void testPingjia6LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    @Test
    public void testPingjia6LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,1);
        white.addPoint(p3);
        Point p4 = new Point(4,12,1);
        white.addPoint(p4);
        Point p5 = new Point(5,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    //test pingjia7
    @Test
    public void testPingjia7Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia7(null);
        });
    }
    @Test
    public void testPingjia7RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(5,2,1);
        black.addPoint(p2);
        Point p3 = new Point(5,3,1);
        black.addPoint(p3);
        Point p4 = new Point(5,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(5,2,1);
        black.addPoint(p2);
        Point p3 = new Point(5,3,1);
        black.addPoint(p3);
        Point p4 = new Point(5,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(5,2,1);
        black.addPoint(p2);
        Point p3 = new Point(5,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(5,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,3,1);
        white.addPoint(p3);
        Point p4 = new Point(5,4,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,1,1);
        black.addPoint(p2);
        Point p3 = new Point(7,1,1);
        black.addPoint(p3);
        Point p4 = new Point(8,1,1);
        black.addPoint(p4);
        Point p5 = new Point(9,1,1);
        black.addPoint(p5);
        Point p6= new Point(10,1,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));

    }
    @Test
    public void testPingjia7ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,1,1);
        black.addPoint(p2);
        Point p3 = new Point(7,1,1);
        black.addPoint(p3);
        Point p4 = new Point(8,1,1);
        black.addPoint(p4);
        Point p5 = new Point(9,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,1,1);
        black.addPoint(p2);
        Point p3 = new Point(7,1,1);
        black.addPoint(p3);
        Point p4 = new Point(8,1,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,1);
        black.addPoint(p2);
        Point p3 = new Point(7,1,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,1,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test //fault detected
    public void testPingjia7ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,1,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,1,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,1,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,1,1);
        white.addPoint(p3);
        Point p4 = new Point(8,1,1);
        white.addPoint(p4);
        Point p5 = new Point(9,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7ColBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(2,1,1);
        black.addPoint(p4);
        Point p5 = new Point(1,1,1);
        black.addPoint(p5);
        Point p6= new Point(0,1,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));

    }
    @Test
    public void testPingjia7ColBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(2,1,1);
        black.addPoint(p4);
        Point p5 = new Point(1,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,1,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        Point p4 = new Point(2,1,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RColBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,1,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,1,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test //fault detected
    public void testPingjia7ColWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,1,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,1,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7ColWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,1,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7ColBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,1,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,1,1);
        white.addPoint(p3);
        Point p4 = new Point(2,1,1);
        white.addPoint(p4);
        Point p5 = new Point(1,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,2,1);
        black.addPoint(p2);
        Point p3 = new Point(7,3,1);
        black.addPoint(p3);
        Point p4 = new Point(8,4,1);
        black.addPoint(p4);
        Point p5 = new Point(9,5,1);
        black.addPoint(p5);
        Point p6= new Point(10,6,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,2,1);
        black.addPoint(p2);
        Point p3 = new Point(7,3,1);
        black.addPoint(p3);
        Point p4 = new Point(8,4,1);
        black.addPoint(p4);
        Point p5 = new Point(9,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,2,1);
        black.addPoint(p2);
        Point p3 = new Point(7,3,1);
        black.addPoint(p3);
        Point p4 = new Point(8,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,2,1);
        black.addPoint(p2);
        Point p3 = new Point(7,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(6,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test //fault detected
    public void testPingjia7LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,1);
        white.addPoint(p3);
        Point p4 = new Point(8,4,1);
        white.addPoint(p4);
        Point p5 = new Point(9,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7LeftDiaBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(2,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        Point p6= new Point(0,6,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(2,4,1);
        black.addPoint(p4);
        Point p5 = new Point(1,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(2,4,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7RLeftDiagBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,2,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(4,2,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test //fault detected
    public void testPingjia7LeftDiagWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,4,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftDiagWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,4,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftdiagWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }
    @Test
    public void testPingjia7LeftdiagWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,2,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7LeftDiagBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,2,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,1);
        white.addPoint(p3);
        Point p4 = new Point(2,4,1);
        white.addPoint(p4);
        Point p5 = new Point(1,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testPingjia7Jinshou()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,1,1);
        black.addPoint(p1);
        Point p2 = new Point(5,2,1);
        black.addPoint(p2);
        Point p3 = new Point(5,3,1);
        black.addPoint(p3);
        Point p4 = new Point(5,4,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Point p6 = new Point(6,1,1);
        black.addPoint(p6);
        Point p7 = new Point(7,1,1);
        black.addPoint(p7);
        Point p8 = new Point(8,1,1);
        black.addPoint(p8);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }


    //test pingjia8
    @Test
    public void testPingjia8Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia8(null);
        });
    }
    @Test
    public void testPingjia8RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,4,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        Point p4 = new Point(15,2,1);
        black.addPoint(p4);
        Point p5 = new Point(15,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,4,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        Point p4 = new Point(15,2,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,4,1);
        black.addPoint(p2);
        Point p3 = new Point(15,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,4,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,2,-1);
        white.addPoint(p4);
        Point p5 = new Point(15,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,2,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,3,1);
        white.addPoint(p3);
        Point p4 = new Point(15,2,1);
        white.addPoint(p4);
        Point p5 = new Point(15,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,6,1);
        black.addPoint(p2);
        Point p3 = new Point(15,7,1);
        black.addPoint(p3);
        Point p4 = new Point(15,8,1);
        black.addPoint(p4);
        Point p5 = new Point(15,9,1);
        black.addPoint(p5);
        Point p6= new Point(15,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));

    }
    @Test
    public void testPingjia8ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,6,1);
        black.addPoint(p2);
        Point p3 = new Point(15,7,1);
        black.addPoint(p3);
        Point p4 = new Point(15,8,1);
        black.addPoint(p4);
        Point p5 = new Point(15,9,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,6,1);
        black.addPoint(p2);
        Point p3 = new Point(15,7,1);
        black.addPoint(p3);
        Point p4 = new Point(15,8,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,6,1);
        black.addPoint(p2);
        Point p3 = new Point(15,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia78olBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(15,6,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test //fault detected
    public void testPingjia8ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,8,-1);
        white.addPoint(p4);
        Point p5 = new Point(15,9,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(15,8,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(15,7,1);
        white.addPoint(p3);
        Point p4 = new Point(15,8,1);
        white.addPoint(p4);
        Point p5 = new Point(15,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8ColBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,5,1);
        black.addPoint(p2);
        Point p3 = new Point(13,5,1);
        black.addPoint(p3);
        Point p4 = new Point(12,5,1);
        black.addPoint(p4);
        Point p5 = new Point(11,5,1);
        black.addPoint(p5);
        Point p6= new Point(10,5,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));

    }
    @Test
    public void testPingjia8ColBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,5,1);
        black.addPoint(p2);
        Point p3 = new Point(13,5,1);
        black.addPoint(p3);
        Point p4 = new Point(12,5,1);
        black.addPoint(p4);
        Point p5 = new Point(11,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,5,1);
        black.addPoint(p2);
        Point p3 = new Point(13,5,1);
        black.addPoint(p3);
        Point p4 = new Point(12,5,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RColBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,5,1);
        black.addPoint(p2);
        Point p3 = new Point(13,5,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test //fault detected
    public void testPingjia8ColWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,5,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,5,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8ColWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia5ColWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,5,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8ColBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,5,1);
        white.addPoint(p3);
        Point p4 = new Point(12,5,1);
        white.addPoint(p4);
        Point p5 = new Point(11,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,6,1);
        black.addPoint(p2);
        Point p3 = new Point(13,7,1);
        black.addPoint(p3);
        Point p4 = new Point(12,8,1);
        black.addPoint(p4);
        Point p5 = new Point(11,9,1);
        black.addPoint(p5);
        Point p6= new Point(10,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,6,1);
        black.addPoint(p2);
        Point p3 = new Point(13,7,1);
        black.addPoint(p3);
        Point p4 = new Point(12,8,1);
        black.addPoint(p4);
        Point p5 = new Point(11,9,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,6,1);
        black.addPoint(p2);
        Point p3 = new Point(13,7,1);
        black.addPoint(p3);
        Point p4 = new Point(12,8,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,6,1);
        black.addPoint(p2);
        Point p3 = new Point(13,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,6,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test //fault detected
    public void testPingjia8LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,8,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,9,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,8,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,6,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,7,1);
        white.addPoint(p3);
        Point p4 = new Point(12,8,1);
        white.addPoint(p4);
        Point p5 = new Point(11,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8LeftDiaBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,2,1);
        black.addPoint(p4);
        Point p5 = new Point(11,1,1);
        black.addPoint(p5);
        Point p6= new Point(10,0,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,2,1);
        black.addPoint(p4);
        Point p5 = new Point(11,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,2,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8RLeftDiagBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test //fault detected
    public void testPingjia8LeftDiagWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,2,-1);
        white.addPoint(p4);
        Point p5 = new Point(11,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftDiagWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(12,2,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftdiagWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }
    @Test
    public void testPingjia8LeftdiagWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8LeftDiagBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,3,1);
        white.addPoint(p3);
        Point p4 = new Point(12,2,1);
        white.addPoint(p4);
        Point p5 = new Point(11,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testPingjia8Jinshou()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(15,5,1);
        black.addPoint(p1);
        Point p2 = new Point(14,4,1);
        black.addPoint(p2);
        Point p3 = new Point(13,3,1);
        black.addPoint(p3);
        Point p4 = new Point(12,2,1);
        black.addPoint(p4);
        Point p5 = new Point(15,6,1);
        black.addPoint(p5);
        Point p6 = new Point(15,7,1);
        black.addPoint(p6);
        Point p7 = new Point(15,8,1);
        black.addPoint(p7);
        Point p8 = new Point(15,9,1);
        black.addPoint(p8);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    //test pingjia9
    @Test
    public void testPingjia9Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia9(null);
        });
    }
    @Test
    public void testPingjia9RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,4,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,2,1);
        black.addPoint(p4);
        Point p5 = new Point(1,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,4,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        Point p4 = new Point(1,2,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,4,1);
        black.addPoint(p2);
        Point p3 = new Point(1,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,4,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,2,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,2,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,3,1);
        white.addPoint(p3);
        Point p4 = new Point(1,2,1);
        white.addPoint(p4);
        Point p5 = new Point(1,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,6,1);
        black.addPoint(p2);
        Point p3 = new Point(1,7,1);
        black.addPoint(p3);
        Point p4 = new Point(1,8,1);
        black.addPoint(p4);
        Point p5 = new Point(1,9,1);
        black.addPoint(p5);
        Point p6= new Point(1,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));

    }
    @Test
    public void testPingjia9ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,6,1);
        black.addPoint(p2);
        Point p3 = new Point(1,7,1);
        black.addPoint(p3);
        Point p4 = new Point(1,8,1);
        black.addPoint(p4);
        Point p5 = new Point(1,9,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,6,1);
        black.addPoint(p2);
        Point p3 = new Point(1,7,1);
        black.addPoint(p3);
        Point p4 = new Point(1,8,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,6,1);
        black.addPoint(p2);
        Point p3 = new Point(1,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9olBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(1,6,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test //fault detected
    public void testPingjia9ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,8,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,9,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,8,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,6,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(1,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(1,7,1);
        white.addPoint(p3);
        Point p4 = new Point(1,8,1);
        white.addPoint(p4);
        Point p5 = new Point(1,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9ColBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,5,1);
        black.addPoint(p2);
        Point p3 = new Point(3,5,1);
        black.addPoint(p3);
        Point p4 = new Point(4,5,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        Point p6= new Point(6,5,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));

    }
    @Test
    public void testPingjia9ColBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,5,1);
        black.addPoint(p2);
        Point p3 = new Point(3,5,1);
        black.addPoint(p3);
        Point p4 = new Point(4,5,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,5,1);
        black.addPoint(p2);
        Point p3 = new Point(3,5,1);
        black.addPoint(p3);
        Point p4 = new Point(4,5,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RColBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,5,1);
        black.addPoint(p2);
        Point p3 = new Point(3,5,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,5,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test //fault detected
    public void testPingjia9ColWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,5,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,5,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,5,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9ColWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia89olBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,5,1);
        white.addPoint(p3);
        Point p4 = new Point(4,5,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,6,1);
        black.addPoint(p2);
        Point p3 = new Point(3,7,1);
        black.addPoint(p3);
        Point p4 = new Point(4,8,1);
        black.addPoint(p4);
        Point p5 = new Point(5,9,1);
        black.addPoint(p5);
        Point p6= new Point(6,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,6,1);
        black.addPoint(p2);
        Point p3 = new Point(3,7,1);
        black.addPoint(p3);
        Point p4 = new Point(4,8,1);
        black.addPoint(p4);
        Point p5 = new Point(5,9,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,6,1);
        black.addPoint(p2);
        Point p3 = new Point(3,7,1);
        black.addPoint(p3);
        Point p4 = new Point(4,8,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,6,1);
        black.addPoint(p2);
        Point p3 = new Point(3,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,6,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test //fault detected
    public void testPingjia9LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,8,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,9,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,8,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,1);
        white.addPoint(p3);
        Point p4 = new Point(4,8,1);
        white.addPoint(p4);
        Point p5 = new Point(5,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9LeftDiaBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        Point p6= new Point(6,0,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(5,1,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9RLeftDiagBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test //fault detected
    public void testPingjia9LeftDiagWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,2,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,1,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftDiagWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(4,2,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftdiagWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }
    @Test
    public void testPingjia9LeftdiagWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9LeftDiagBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,3,1);
        white.addPoint(p3);
        Point p4 = new Point(4,2,1);
        white.addPoint(p4);
        Point p5 = new Point(5,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testPingjia9Jinshou()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(1,5,1);
        black.addPoint(p1);
        Point p2 = new Point(2,4,1);
        black.addPoint(p2);
        Point p3 = new Point(3,3,1);
        black.addPoint(p3);
        Point p4 = new Point(4,2,1);
        black.addPoint(p4);
        Point p5 = new Point(1,6,1);
        black.addPoint(p5);
        Point p6 = new Point(1,7,1);
        black.addPoint(p6);
        Point p7 = new Point(1,8,1);
        black.addPoint(p7);
        Point p8 = new Point(1,9,1);
        black.addPoint(p8);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    //test pingjia10
    @Test
    public void testPingjia10Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia10(null);
        });
    }
    @Test
    public void testPingjia10RowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(5,14,1);
        black.addPoint(p2);
        Point p3 = new Point(5,13,1);
        black.addPoint(p3);
        Point p4 = new Point(5,12,1);
        black.addPoint(p4);
        Point p5 = new Point(5,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(1,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(5,14,1);
        black.addPoint(p2);
        Point p3 = new Point(5,13,1);
        black.addPoint(p3);
        Point p4 = new Point(5,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(5,14,1);
        black.addPoint(p2);
        Point p3 = new Point(5,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(5,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(-1,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10RowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,13,1);
        white.addPoint(p3);
        Point p4 = new Point(5,12,1);
        white.addPoint(p4);
        Point p5 = new Point(5,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,15,1);
        black.addPoint(p2);
        Point p3 = new Point(7,15,1);
        black.addPoint(p3);
        Point p4 = new Point(8,15,1);
        black.addPoint(p4);
        Point p5 = new Point(9,15,1);
        black.addPoint(p5);
        Point p6= new Point(10,15,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));

    }
    @Test
    public void testPingjia10ColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,15,1);
        black.addPoint(p2);
        Point p3 = new Point(7,15,1);
        black.addPoint(p3);
        Point p4 = new Point(8,15,1);
        black.addPoint(p4);
        Point p5 = new Point(9,15,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,15,1);
        black.addPoint(p2);
        Point p3 = new Point(7,15,1);
        black.addPoint(p3);
        Point p4 = new Point(8,15,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,15,1);
        black.addPoint(p2);
        Point p3 = new Point(7,15,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10olBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,15,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test //fault detected
    public void testPingjia10ColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,15,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,15,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,15,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,15,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10ColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,15,1);
        white.addPoint(p3);
        Point p4 = new Point(8,15,1);
        white.addPoint(p4);
        Point p5 = new Point(9,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10ColBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(2,15,1);
        black.addPoint(p4);
        Point p5 = new Point(1,15,1);
        black.addPoint(p5);
        Point p6= new Point(0,15,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));

    }
    @Test
    public void testPingjia10ColBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(2,15,1);
        black.addPoint(p4);
        Point p5 = new Point(1,15,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        Point p4 = new Point(2,15,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RColBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,15,1);
        black.addPoint(p2);
        Point p3 = new Point(3,15,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,15,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test //fault detected
    public void testPingjia10ColWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,15,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,15,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,15,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10ColWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,15,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10olBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,15,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,15,1);
        white.addPoint(p3);
        Point p4 = new Point(2,15,1);
        white.addPoint(p4);
        Point p5 = new Point(1,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10LeftDiaBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        Point p3 = new Point(7,13,1);
        black.addPoint(p3);
        Point p4 = new Point(8,12,1);
        black.addPoint(p4);
        Point p5 = new Point(9,11,1);
        black.addPoint(p5);
        Point p6= new Point(10,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        Point p3 = new Point(7,13,1);
        black.addPoint(p3);
        Point p4 = new Point(8,12,1);
        black.addPoint(p4);
        Point p5 = new Point(9,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        Point p3 = new Point(7,13,1);
        black.addPoint(p3);
        Point p4 = new Point(8,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        Point p3 = new Point(7,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test //fault detected
    public void testPingjia10LeftDiagWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,13,1);
        white.addPoint(p3);
        Point p4 = new Point(8,12,1);
        white.addPoint(p4);
        Point p5 = new Point(9,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10LeftDiaBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(2,12,1);
        black.addPoint(p4);
        Point p5 = new Point(1,11,1);
        black.addPoint(p5);
        Point p6= new Point(0,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(2,12,1);
        black.addPoint(p4);
        Point p5 = new Point(1,11,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        Point p4 = new Point(2,12,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10RLeftDiagBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,14,1);
        black.addPoint(p2);
        Point p3 = new Point(3,13,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(4,14,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test //fault detected
    public void testPingjia10LeftDiagWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,12,-1);
        white.addPoint(p4);
        Point p5 = new Point(1,11,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftDiagWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,12,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftdiagWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }
    @Test
    public void testPingjia10LeftdiagWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,14,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10LeftDiagBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(4,14,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,1);
        white.addPoint(p3);
        Point p4 = new Point(2,12,1);
        white.addPoint(p4);
        Point p5 = new Point(1,11,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testPingjia10Jinshou()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,15,1);
        black.addPoint(p1);
        Point p2 = new Point(6,14,1);
        black.addPoint(p2);
        Point p3 = new Point(7,13,1);
        black.addPoint(p3);
        Point p4 = new Point(8,12,1);
        black.addPoint(p4);
        Point p5 = new Point(5,15,1);
        black.addPoint(p5);
        Point p6 = new Point(5,14,1);
        black.addPoint(p6);
        Point p7 = new Point(5,13,1);
        black.addPoint(p7);
        Point p8 = new Point(5,12,1);
        black.addPoint(p8);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    //test pingjia9
    @Test
    public void testPingjiaNull()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia(null);
        });
    }
    @Test
    public void testPingjiaRowBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,9,1);
        black.addPoint(p1);
        Point p2 = new Point(5,8,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        Point p4 = new Point(5,6,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,9,1);
        black.addPoint(p1);
        Point p2 = new Point(5,8,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        Point p4 = new Point(5,6,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,9,1);
        black.addPoint(p1);
        Point p2 = new Point(5,8,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,9,1);
        black.addPoint(p1);
        Point p2 = new Point(5,8,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowBlack1()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,9,1);
        black.addPoint(p1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,6,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,6,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowWhite1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaRowbBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,1);
        white.addPoint(p3);
        Point p4 = new Point(5,6,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColBlack6()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(5,6,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        Point p4 = new Point(5,8,1);
        black.addPoint(p4);
        Point p5 = new Point(5,9,1);
        black.addPoint(p5);
        Point p6= new Point(5,10,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));

    }
    @Test
    public void testPingjiaColBlack5()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(5,6,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        Point p4 = new Point(5,8,1);
        black.addPoint(p4);
        Point p5 = new Point(5,9,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(5,6,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        Point p4 = new Point(5,8,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRColBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(5,6,1);
        black.addPoint(p2);
        Point p3 = new Point(5,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaolBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(5,6,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test //fault detected
    public void testPingjiaColWhite5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,8,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,9,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(5,8,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,6,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaColBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(5,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(5,7,1);
        white.addPoint(p3);
        Point p4 = new Point(5,8,1);
        white.addPoint(p4);
        Point p5 = new Point(5,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaColBlack6Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(6,5,1);
        black.addPoint(p2);
        Point p3 = new Point(7,5,1);
        black.addPoint(p3);
        Point p4 = new Point(8,5,1);
        black.addPoint(p4);
        Point p5 = new Point(9,5,1);
        black.addPoint(p5);
        Point p6= new Point(10,5,1);
        black.addPoint(p6);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));

    }
    @Test
    public void testPingjiaColBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(6,5,1);
        black.addPoint(p2);
        Point p3 = new Point(7,5,1);
        black.addPoint(p3);
        Point p4 = new Point(8,5,1);
        black.addPoint(p4);
        Point p5 = new Point(9,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(6,5,1);
        black.addPoint(p2);
        Point p3 = new Point(7,5,1);
        black.addPoint(p3);
        Point p4 = new Point(8,5,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRColBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(6,5,1);
        black.addPoint(p2);
        Point p3 = new Point(7,5,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(5,5,1);
        black.addPoint(p1);
        Point p2 = new Point(6,5,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test //fault detected
    public void testPingjiaColWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,5,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,5,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,5,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,5,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaColWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,5,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,5,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,5,1);
        white.addPoint(p3);
        Point p4 = new Point(8,5,1);
        white.addPoint(p4);
        Point p5 = new Point(9,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagBlack4Up()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(4,6,1);
        black.addPoint(p1);
        Point p2 = new Point(3,7,1);
        black.addPoint(p2);
        Point p3 = new Point(2,8,1);
        black.addPoint(p3);
        Point p4 = new Point(1,9,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRLeftDiagBlack3Up()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(4,6,1);
        black.addPoint(p1);
        Point p2 = new Point(3,7,1);
        black.addPoint(p2);
        Point p3 = new Point(2,8,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftDiagBlack2Up()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(4,6,1);
        black.addPoint(p1);
        Point p2 = new Point(3,7,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagWhite4Up()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);

        Point p1 = new Point(4,6,-1);
        white.addPoint(p1);
        Point p2 = new Point(3,7,-1);
        white.addPoint(p2);
        Point p3 = new Point(2,8,-1);
        white.addPoint(p3);
        Point p4 = new Point(1,9,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite3Up()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(4,6,-1);
        white.addPoint(p1);
        Point p2 = new Point(3,7,-1);
        white.addPoint(p2);
        Point p3 = new Point(2,8,-1);
        white.addPoint(p3);

        int [][] d = white.getDatas();
        d[5][5] = 40;

        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite2Up()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(4,6,-1);
        white.addPoint(p1);
        Point p2 = new Point(3,7,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagBlackWhite2plus3Up()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p2 = new Point(4,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(2,8,1);
        white.addPoint(p4);
        Point p5 = new Point(1,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }


    @Test
    public void testPingjiaLeftDiagBlack4()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(6,4,1);
        black.addPoint(p1);
        Point p2 = new Point(7,3,1);
        black.addPoint(p2);
        Point p3 = new Point(8,2,1);
        black.addPoint(p3);
        Point p4 = new Point(9,1,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRLeftDiagBlack3()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(6,4,1);
        black.addPoint(p1);
        Point p2 = new Point(7,3,1);
        black.addPoint(p2);
        Point p3 = new Point(8,2,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftDiagBlack2()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(6,4,1);
        black.addPoint(p1);
        Point p2 = new Point(7,3,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagWhite4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);

        Point p1 = new Point(6,4,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,3,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,2,-1);
        white.addPoint(p3);
        Point p4 = new Point(9,1,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,4,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,3,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,2,-1);
        white.addPoint(p3);

        int [][] d = white.getDatas();
        d[5][5] = 40;

        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,4,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,3,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p2 = new Point(6,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,2,1);
        white.addPoint(p4);
        Point p5 = new Point(9,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiaBlack5Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(9,9,1);
        black.addPoint(p1);
        Point p2 = new Point(8,8,1);
        black.addPoint(p2);
        Point p3 = new Point(7,7,1);
        black.addPoint(p3);
        Point p4 = new Point(6,6,1);
        black.addPoint(p4);
        Point p5 = new Point(5,5,1);
        black.addPoint(p5);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagBlack4Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(9,9,1);
        black.addPoint(p1);
        Point p2 = new Point(8,8,1);
        black.addPoint(p2);
        Point p3 = new Point(7,7,1);
        black.addPoint(p3);
        Point p4 = new Point(6,6,1);
        black.addPoint(p4);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaRLeftDiagBlack3Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(9,9,1);
        black.addPoint(p1);
        Point p2 = new Point(8,8,1);
        black.addPoint(p2);
        Point p3 = new Point(7,7,1);
        black.addPoint(p3);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftDiagBlack2Down()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(9,9,1);
        black.addPoint(p1);
        Point p2 = new Point(8,8,1);
        black.addPoint(p2);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test //fault detected
    public void testPingjiaLeftDiagWhite5Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(8,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(6,6,-1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftDiagWhite4Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(8,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(6,6,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(8,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaLeftdiagWhite2Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(8,8,-1);
        white.addPoint(p2);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaLeftDiagBlackWhite2plus3Down()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(8,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,1);
        white.addPoint(p3);
        Point p4 = new Point(6,6,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaJinshou()
    {
        Calculate black = new Calculate(1);
        Point p1 = new Point(9,9,1);
        black.addPoint(p1);
        Point p2 = new Point(8,8,1);
        black.addPoint(p2);
        Point p3 = new Point(7,7,1);
        black.addPoint(p3);
        Point p4 = new Point(6,6,1);
        black.addPoint(p4);
        Point p5 = new Point(5,9,1);
        black.addPoint(p5);
        Point p6 = new Point(6,8,1);
        black.addPoint(p6);
        Point p7 = new Point(7,7,1);
        black.addPoint(p7);
        Point p8 = new Point(8,6,1);
        black.addPoint(p8);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaMixColDown5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(9,6,-1);
        white.addPoint(p1);
        Point p2 = new Point(9,7,-1);
        white.addPoint(p2);
        Point p3 = new Point(9,8,1);
        white.addPoint(p3);
        Point p4 = new Point(9,9,1);
        white.addPoint(p4);
        Point p5 = new Point(9,10,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaMixDiaDown5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p2 = new Point(6,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-1);
        white.addPoint(p3);
        Point p4 = new Point(8,8,1);
        white.addPoint(p4);
        Point p5 = new Point(9,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        d[5][5] = 40;
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaMixRowDown5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,1);
        white.addPoint(p3);
        Point p4 = new Point(9,9,1);
        white.addPoint(p4);
        Point p5 = new Point(10,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testPingjiaBlackRowDown4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,1);
        white.addPoint(p3);
        Point p4 = new Point(9,9,1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaBlackRowDown3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }


    @Test
    public void testPingjiaWhiteRowDown4()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,-1);
        white.addPoint(p3);
        Point p4 = new Point(9,9,-1);
        white.addPoint(p4);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaWhiteRowDown5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,-1);
        white.addPoint(p3);
        Point p4 = new Point(9,9,-1);
        white.addPoint(p4);
        Point p5 = new Point(9,10,-1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaWhiteRowDown3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,-1);
        white.addPoint(p2);
        Point p3 = new Point(8,9,-1);
        white.addPoint(p3);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaWhiteRowDown2()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(7,9,-1);
        white.addPoint(p2);

        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaWhiteRowDown1()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(6,9,-1);
        white.addPoint(p1);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }
    @Test
    public void testPingjiaFull()
    {
        Calculate black = new Calculate(1);
        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 19; j++) {
                black.addPoint(new Point(i, j, 1));
            }
        }
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }


    //test getNext
    @Test
    public void testGetNextNull()
    {
        Calculate black = null;
        Assertions.assertThrows(NullPointerException.class,()->{
            black.getNext();
        });
    }
    @Test
    public void testGetNextBlack()
    {
        Calculate black = new Calculate(1);
        black.addPoint(new Point(0,0,1));
        Point p = black.getNext();
        Assertions.assertTrue(p.getX() >=0 && p.getX() < 20 && p.getY() >=0 && p.getY()<20 );
    }
    @Test
    public void testGetNextWhite()
    {
        Calculate white = new Calculate(-1);
        white.addPoint(new Point(0,0,-1));
        Point p = white.getNext();
        Assertions.assertTrue(p.getX() >=0 && p.getX() < 20 && p.getY() >=0 && p.getY()<20 );
    }
}


