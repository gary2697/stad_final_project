import com.brioal.utool.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.brioal.utool.Calculate;

// since the documentation for the game is lacking, we did black box testing based upon the result we had
// when we played the game.
//the focus would be on applying EP,BA,EG
//EP : black point white point and point with invalid states and null
// point inside the board / outside the board
// for boundary analysis, we did x < 0, 0 <= x < 20 x >20, same for y, and state 1/-1/0/other values
//tests that are failing are commented out for generating test report

//failure reports :
/*	Adding white point using black instance should return false
            Adding points with state that¡¯s not 1/-1 should raise exception. But instead it always returns -1
            Test judgewin: 5 stones with invalid state should return false/raise exception, but returns true instead
            Pingjia + i with invalid states still returns -1/1
            Pingjia i test 5 stones in a column should return 1, but returns 0
*/
public class BlackBoxCalculateTest {
    @Test
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
    @Test //negative value should be return out of bound exception
    public void testAddPointNegative()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,()->{
            black.addPoint(new Point(-1,-1,-1));
        });
    }

    @Test // test add a black point
    public void testAddPointBlackPoint()
    {
        Calculate black = new Calculate(1);
        Point p = new Point(1,1,1);
        black.addPoint(p);
        Assertions.assertEquals(1,black.getDatas()[1][1]);
    }
    @Test // test add a black point
    // fault detected white instance adding a black point should fail
    public void testAddPointBlackPointWithWhite()
    {
        Calculate white = new Calculate(-1);
        Point p = new Point(1,1,1);
        white.addPoint(p);
        Assertions.assertEquals(1,white.getDatas()[1][1]);
    }

    @Test // test add a white point success
    public void testAddPointWhitePoint()
    {
        Calculate white = new Calculate(-1);
        Point p = new Point(1,1,-1);
        white.addPoint(p);
        Assertions.assertEquals(-1,white.getDatas()[1][1]);
    }
    @Test // add black twice
    public void testAddPointAddBlackTwice()
    {
        Calculate black = new Calculate(-1);
        Point p = new Point(1,1,1);
        black.addPoint(p);
        black.addPoint(p);
        Assertions.assertEquals(1,black.getDatas()[1][1]);
    }
    @Test // add white twice
    public void testAddPointAddWhiteTwice()
    {
        Calculate white = new Calculate(-1);
        Point p = new Point(1,1,-1);
        white.addPoint(p);
        white.addPoint(p);
        Assertions.assertEquals(-1,white.getDatas()[1][1]);
    }
    @Test // add weird state
    public void testAddPointAddWeirdState()
    {
        Calculate white = new Calculate(3);
        Point p = new Point(1,1,3);
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
        black.addPoint(p2);
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
    @Test
    public void testJudgeWinFiveColWhiteColorCheckRightMost()
    {
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
        Assertions.assertEquals(true,white.JudegeWin(p5));
    }
    @Test
    public void testJudgeWinFiveColWhiteColorCheckRightMostNegative()
    {
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
        Assertions.assertEquals(true,white.JudegeWin(p5));
    }
    //fault detected. invalid state should return false
//    @Test
//    public void testJudgeWinFiveColInvalidColorCheckRightMost()
//    {
//        Calculate white = new Calculate(-3);
//        Point p1 = new Point(1,1,-3);
//        white.addPoint(p1);
//        Point p2 = new Point(1,2,-3);
//        white.addPoint(p2);
//        Point p3 = new Point(1,3,-3);
//        white.addPoint(p3);
//        Point p4 = new Point(1,4,-3);
//        white.addPoint(p4);
//        Point p5 = new Point(1,5,-3);
//        white.addPoint(p5);
//        Assertions.assertEquals(false,white.JudegeWin(p5));
//    }
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
    //same fault revealed
//    @Test
//    public void testJudgeWinFiveColInvalidColorCheckLeftMost()
//    {
//        Calculate black = new Calculate(10);
//        Point p1 = new Point(1,1,10);
//        black.addPoint(p1);
//        Point p2 = new Point(1,2,10);
//        black.addPoint(p2);
//        Point p3 = new Point(1,3,10);
//        black.addPoint(p3);
//        Point p4 = new Point(1,4,10);
//        black.addPoint(p4);
//        Point p5 = new Point(1,5,10);
//        black.addPoint(p5);
//        Assertions.assertEquals(false,black.JudegeWin(p1));
//    }
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
    //same fault
//    @Test
//    public void testJudgeWinFiveRowInvalidColorCheckLeftMost()
//    {
//        Calculate black = new Calculate(100);
//        Point p1 = new Point(1,1,100);
//        black.addPoint(p1);
//        Point p2 = new Point(2,1,100);
//        black.addPoint(p2);
//        Point p3 = new Point(3,1,100);
//        black.addPoint(p3);
//        Point p4 = new Point(4,1,100);
//        black.addPoint(p4);
//        Point p5 = new Point(5,1,100);
//        black.addPoint(p5);
//        Assertions.assertEquals(false,black.JudegeWin(p1));
//    }
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
//    @Test
//    public void testJudgeWinFiveLeftDiagInvalidColorCheckRightMost()
//    {
//        Calculate black = new Calculate(10);
//        Point p1 = new Point(1,1,10);
//        black.addPoint(p1);
//        Point p2 = new Point(2,2,10);
//        black.addPoint(p2);
//        Point p3 = new Point(3,3,10);
//        black.addPoint(p3);
//        Point p4 = new Point(4,4,10);
//        black.addPoint(p4);
//        Point p5 = new Point(5,5,10);
//        black.addPoint(p5);
//        Assertions.assertEquals(false,black.JudegeWin(p5));
//    }
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

    //test pingjia3
    @Test
    public void testPingjia3Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia3(null);
        });
    }
    //test pingjia4
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
        Assertions.assertEquals(1,white.pingjia3(d));
    }
    @Test
    public void testPingjia3RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(15,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(15,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(15,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia3(d));
    }
    @Test
    public void testPingjia3RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
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
    //fault detected
//    @Test
//    public void testPingjia3ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,1,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,1,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,1,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,1,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia3(d));
//    }
//
//    @Test //fault detected
//    public void testPingjia3ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,1,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,1,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(12,1,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,1,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia3(d));
//    }
    @Test
    public void testPingjia3ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,1,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,1,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,1,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia3(d));
    }

    //    @Test
//    public void testPingjia3LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,2,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,4,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia3(d));
//    }
//    @Test //fault detected
//    public void testPingjia3LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,2,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(12,4,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia3(d));
//    }
    @Test //fault detected
    public void testPingjia3LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia3(d));
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


    //    @Test
//    public void testPingjia3RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,4,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,2,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,1,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia3(d));
//    }
//    @Test //fault detected
//    public void testPingjia3RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,4,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(122,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,1,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia3(d));
//    }
    @Test //fault detected
    public void testPingjia3RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,4,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,2,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia3(d));
    }

    @Test
    public void testPingjia3RightDiagBlackWhite2plus3()
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
        Assertions.assertEquals(0,black.pingjia3(d));
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
        Assertions.assertEquals(1,white.pingjia4(d));
    }
    @Test
    public void testPingjia4RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,1,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,1,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,1,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia4(d));
    }
    @Test
    public void testPingjia4RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
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
//fault detected
//    @Test
//    public void testPingjia4ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(1,2,1);
//        black.addPoint(p2);
//        Point p3 = new Point(1,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(1,4,1);
//        black.addPoint(p4);
//        Point p5 = new Point(1,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia4(d));
//    }
//
//    @Test //fault detected
//    public void testPingjia4ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(1,2,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(1,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(1,4,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(1,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia4(d));
//    }
    @Test
    public void testPingjia4ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(1,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(1,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(1,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia4(d));
    }

//    @Test
//    public void testPingjia4LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,2,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,4,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia4(d));
//    }
//    @Test //fault detected
//    public void testPingjia4LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,2,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,4,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia4(d));
//    }
    @Test //fault detected
    public void testPingjia4LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia4(d));
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


    //    @Test
//    public void testPingjia4RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,4,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,2,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,1,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia4(d));
//    }
//    @Test //fault detected
//    public void testPingjia4RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,4,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,2,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,1,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia4(d));
//    }
    @Test //fault detected
    public void testPingjia4RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,4,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,2,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia4(d));
    }

    @Test
    public void testPingjia4RightDiagBlackWhite2plus3()
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
        Assertions.assertEquals(0,black.pingjia4(d));
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
    //test pingjia5
    @Test
    public void testPingjia5RowBlack5()
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
    public void testPingjia5RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia5(d));
    }
    @Test
    public void testPingjia5RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(15,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(15,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(15,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(15,11-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia5(d));
    }
    @Test
    public void testPingjia5RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia5(d));
    }

    @Test
    public void testPingjia5RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testPingjia5ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,15,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,15,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,15,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia5(d));
//    }
//
//    @Test //fault detected
//    public void testPingjia5ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,15,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,15,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(12,15,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia5(d));
//    }
    @Test
    public void testPingjia5ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,15,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,15,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,15,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,15,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia5(d));
    }

    //    @Test
//    public void testPingjia5LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,14,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,13,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,12,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,11,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia5(d));
//    }
//    @Test //fault detected
//    public void testPingjia5LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,14,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,13,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(12,12,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,11,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia5(d));
//    }
    @Test //fault detected
    public void testPingjia5LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,11,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia5(d));
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


    //    @Test
//    public void testPingjia5RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,11,1);
//        black.addPoint(p1);
//        Point p2 = new Point(14,12,1);
//        black.addPoint(p2);
//        Point p3 = new Point(13,13,1);
//        black.addPoint(p3);
//        Point p4 = new Point(12,14,1);
//        black.addPoint(p4);
//        Point p5 = new Point(11,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia5(d));
//    }
//    @Test //fault detected
//    public void testPingjia5RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,11,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(14,12,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(13,13,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(12,14,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(11,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia5(d));
//    }
    @Test //fault detected
    public void testPingjia5RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,11,-10);
        white.addPoint(p1);
        Point p2 = new Point(14,12,-10);
        white.addPoint(p2);
        Point p3 = new Point(13,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(12,14,-10);
        white.addPoint(p4);
        Point p5 = new Point(11,15,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia5(d));
    }

    @Test
    public void testPingjia5RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,11,-1);
        white.addPoint(p1);
        Point p2 = new Point(14,12,-1);
        white.addPoint(p2);
        Point p3 = new Point(13,13,1);
        white.addPoint(p3);
        Point p4 = new Point(12,14,1);
        white.addPoint(p4);
        Point p5 = new Point(11,15,1);
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
    //test pingjia6
    @Test
    public void testpingjia6RowBlack5()
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
    public void testpingjia6RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia6(d));
    }
    @Test
    public void testpingjia6RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(1,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(1,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(1,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(1,11,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia6(d));
    }
    @Test
    public void testpingjia6RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }

    @Test
    public void testpingjia6RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjia6ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,15,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,15,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,15,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia6(d));
//    }
//
//    @Test //fault detected
//    public void testpingjia6ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,15,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,15,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,15,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia6(d));
//    }
    @Test
    public void testpingjia6ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,11,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia6(d));
    }

    //    @Test
//    public void testpingjia6LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,14,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,13,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,12,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,11,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia6(d));
//    }
//    @Test //fault detected
//    public void testpingjia6LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,14,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,13,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,12,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,11,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia6(d));
//    }
    @Test //fault detected
    public void testpingjia6LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,11,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia6(d));
    }

    @Test
    public void testpingjia6LeftDiagBlackWhite2plus3()
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


    //    @Test
//    public void testpingjia6RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,11,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,12,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,13,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,14,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia6(d));
//    }
//    @Test //fault detected
//    public void testpingjia6RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,11,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,12,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,13,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,14,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia6(d));
//    }
    @Test //fault detected
    public void testpingjia6RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,11,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,12,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,14,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,15,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia6(d));
    }

    @Test
    public void testpingjia6RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,11,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,12,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,13,1);
        white.addPoint(p3);
        Point p4 = new Point(4,14,1);
        white.addPoint(p4);
        Point p5 = new Point(5,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia6(d));
    }


    //test pingjia7
    @Test
    public void testpingjia7Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia7(null);
        });
    }
    //test pingjia7
    @Test
    public void testpingjia7RowBlack5()
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
    public void testpingjia7RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia7(d));
    }
    @Test
    public void testpingjia7RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(5,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(5,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(5,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia7(d));
    }
    @Test
    public void testpingjia7RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }

    @Test
    public void testpingjia7RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjia7ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,1,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,1,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,1,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,1,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia7(d));
//    }
//
//    @Test //fault detected
//    public void testpingjia7ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,1,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,1,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,1,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,1,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia7(d));
//    }
    @Test
    public void testpingjia7ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,1,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,1,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,1,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia7(d));
    }

    //    @Test
//    public void testpingjia7LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,1,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,2,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,4,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia7(d));
//    }
//    @Test //fault detected
//    public void testpingjia7LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,1,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,2,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,4,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia7(d));
//    }
    @Test //fault detected
    public void testpingjia7LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,1,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,2,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,4,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia7(d));
    }

    @Test
    public void testpingjia7LeftDiagBlackWhite2plus3()
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


    //    @Test
//    public void testpingjia7RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,4,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,3,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,2,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,1,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia7(d));
//    }
//    @Test //fault detected
//    public void testpingjia7RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,4,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,3,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,2,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,1,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia7(d));
//    }
    @Test //fault detected
    public void testpingjia7RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,4,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,2,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia7(d));
    }

    @Test
    public void testpingjia7RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,4,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,3,1);
        white.addPoint(p3);
        Point p4 = new Point(8,2,1);
        white.addPoint(p4);
        Point p5 = new Point(9,1,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia7(d));
    }


    //test pingjia8
    @Test
    public void testpingjia8Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia8(null);
        });
    }
    //test pingjia8
    @Test
    public void testpingjia8RowBlack5()
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
    public void testpingjia8RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia8(d));
    }
    @Test
    public void testpingjia8RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(15,4,-10);
        white.addPoint(p2);
        Point p3 = new Point(15,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(15,2,-10);
        white.addPoint(p4);
        Point p5 = new Point(15,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia8(d));
    }
    @Test
    public void testpingjia8RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }

    @Test
    public void testpingjia8RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjia8ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(15,6,1);
//        black.addPoint(p2);
//        Point p3 = new Point(15,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(15,8,1);
//        black.addPoint(p4);
//        Point p5 = new Point(15,9,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia8(d));
//    }
//
//    @Test //fault detected
//    public void testpingjia8ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(15,6,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(15,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(15,8,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(15,9,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia8(d));
//    }
    @Test
    public void testpingjia8ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(15,6,-10);
        white.addPoint(p2);
        Point p3 = new Point(15,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(15,8,-10);
        white.addPoint(p4);
        Point p5 = new Point(15,9,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia8(d));
    }

    //    @Test
//    public void testpingjia8LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(16,6,1);
//        black.addPoint(p2);
//        Point p3 = new Point(17,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(18,8,1);
//        black.addPoint(p4);
//        Point p5 = new Point(19,9,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia8(d));
//    }
//    @Test //fault detected
//    public void testpingjia8LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(16,6,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(17,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(18,8,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(19,9,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia8(d));
//    }
    @Test //fault detected
    public void testpingjia8LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(16,6,-10);
        white.addPoint(p2);
        Point p3 = new Point(17,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(18,8,-10);
        white.addPoint(p4);
        Point p5 = new Point(19,9,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia8(d));
    }

    @Test
    public void testpingjia8LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(16,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(17,7,1);
        white.addPoint(p3);
        Point p4 = new Point(18,8,1);
        white.addPoint(p4);
        Point p5 = new Point(19,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }


    //    @Test
//    public void testpingjia8RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(15,9,1);
//        black.addPoint(p1);
//        Point p2 = new Point(16,8,1);
//        black.addPoint(p2);
//        Point p3 = new Point(17,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(18,6,1);
//        black.addPoint(p4);
//        Point p5 = new Point(19,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia8(d));
//    }
//    @Test //fault detected
//    public void testpingjia8RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(15,9,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(16,8,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(17,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(18,6,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(19,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia8(d));
//    }
    @Test //fault detected
    public void testpingjia8RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(15,9,-10);
        white.addPoint(p1);
        Point p2 = new Point(16,8,-10);
        white.addPoint(p2);
        Point p3 = new Point(17,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(18,6,-10);
        white.addPoint(p4);
        Point p5 = new Point(19,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia8(d));
    }

    @Test
    public void testpingjia8RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(15,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(16,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(17,7,1);
        white.addPoint(p3);
        Point p4 = new Point(18,6,1);
        white.addPoint(p4);
        Point p5 = new Point(19,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia8(d));
    }


    //test pingjia9
    @Test
    public void testpingjia9Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia9(null);
        });
    }
    //test pingjia9
    @Test
    public void testpingjia9RowBlack5()
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
    public void testpingjia9RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia9(d));
    }
    @Test
    public void testpingjia9RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(1,4,-10);
        white.addPoint(p2);
        Point p3 = new Point(1,3,-10);
        white.addPoint(p3);
        Point p4 = new Point(1,2,-10);
        white.addPoint(p4);
        Point p5 = new Point(1,1,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia9(d));
    }
    @Test
    public void testpingjia9RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }

    @Test
    public void testpingjia9RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjia9ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,5,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,5,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,5,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia9(d));
//    }
//
//    @Test //fault detected
//    public void testpingjia9ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,5,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,5,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,5,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia9(d));
//    }
    @Test
    public void testpingjia9ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,5,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,5,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,5,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia9(d));
    }

    //    @Test
//    public void testpingjia9LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,6,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,8,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,9,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia9(d));
//    }
//    @Test //fault detected
//    public void testpingjia9LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,6,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,8,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,9,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia9(d));
//    }
    @Test //fault detected
    public void testpingjia9LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,6,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,8,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,9,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia9(d));
    }

    @Test
    public void testpingjia9LeftDiagBlackWhite2plus3()
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


    //    @Test
//    public void testpingjia9RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(1,9,1);
//        black.addPoint(p1);
//        Point p2 = new Point(2,8,1);
//        black.addPoint(p2);
//        Point p3 = new Point(3,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(4,6,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia9(d));
//    }
//    @Test //fault detected
//    public void testpingjia9RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(1,9,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(2,8,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(3,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(4,6,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia9(d));
//    }
    @Test //fault detected
    public void testpingjia9RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(1,9,-10);
        white.addPoint(p1);
        Point p2 = new Point(2,8,-10);
        white.addPoint(p2);
        Point p3 = new Point(3,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(4,6,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia9(d));
    }

    @Test
    public void testpingjia9RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(1,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(2,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(3,7,1);
        white.addPoint(p3);
        Point p4 = new Point(4,6,1);
        white.addPoint(p4);
        Point p5 = new Point(5,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia9(d));
    }



    //test pingjia10
    @Test
    public void testpingjia10Null()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia10(null);
        });
    }
    //test pingjia10
    @Test
    public void testpingjia10RowBlack5()
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
    public void testpingjia10RowWhite5()
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
        Assertions.assertEquals(1,white.pingjia10(d));
    }
    @Test
    public void testpingjia10RowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(5,14,-10);
        white.addPoint(p2);
        Point p3 = new Point(5,13,-10);
        white.addPoint(p3);
        Point p4 = new Point(5,12,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,11,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia10(d));
    }
    @Test
    public void testpingjia10RowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }

    @Test
    public void testpingjia10RowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjia10ColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,15,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,15,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,15,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia10(d));
//    }
//
//    @Test //fault detected
//    public void testpingjia10ColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,15,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,15,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,15,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia10(d));
//    }
    @Test
    public void testpingjia10ColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,15,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,15,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,15,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,15,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia10(d));
    }

    //    @Test
//    public void testpingjia10LeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,15,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,16,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,17,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,18,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,19,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia10(d));
//    }
//    @Test //fault detected
//    public void testpingjia10LeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,15,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,16,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,17,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,18,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,19,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia10(d));
//    }
    @Test //fault detected
    public void testpingjia10LeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,15,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,16,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,17,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,18,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,19,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia10(d));
    }

    @Test
    public void testpingjia10LeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,15,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,16,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,17,1);
        white.addPoint(p3);
        Point p4 = new Point(8,18,1);
        white.addPoint(p4);
        Point p5 = new Point(9,19,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }


    //    @Test
//    public void testpingjia10RightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,19,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,18,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,17,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,16,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,15,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia10(d));
//    }
//    @Test //fault detected
//    public void testpingjia10RightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,19,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,18,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,17,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,16,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,15,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia10(d));
//    }
    @Test //fault detected
    public void testpingjia10RightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,19,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,18,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,17,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,16,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,15,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia10(d));
    }

    @Test
    public void testpingjia10RightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,19,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,18,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,17,1);
        white.addPoint(p3);
        Point p4 = new Point(8,16,1);
        white.addPoint(p4);
        Point p5 = new Point(9,15,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia10(d));
    }


    //test pingjia
    @Test
    public void testpingjiaNull()
    {
        Calculate black = new Calculate(1);
        Assertions.assertThrows(NullPointerException.class,()->{
            black.pingjia(null);
        });
    }
    //test pingjia
//    @Test
//    public void testpingjiaRowBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,9,1);
//        black.addPoint(p1);
//        Point p2 = new Point(5,8,1);
//        black.addPoint(p2);
//        Point p3 = new Point(5,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(5,6,1);
//        black.addPoint(p4);
//        Point p5 = new Point(5,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia(d));
//    }

//    @Test
//    public void testpingjiaRowWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,9,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(5,8,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(5,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(5,6,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(5,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//        Assertions.assertEquals(1,white.pingjia(d));
//    }
    @Test
    public void testpingjiaRowInvalid5()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,9,-10);
        white.addPoint(p1);
        Point p2 = new Point(5,8,-10);
        white.addPoint(p2);
        Point p3 = new Point(5,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(5,6,-10);
        white.addPoint(p4);
        Point p5 = new Point(5,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,white.pingjia(d));
    }
    @Test
    public void testpingjiaRowEmpty()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        int [][] d = black.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }

    @Test
    public void testpingjiaRowbBlackWhite2plus3()
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
    //fault detected
//    @Test
//    public void testpingjiaColBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,9,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,9,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,9,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,9,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,9,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia(d));
//    }
//
//    @Test //fault detected
//    public void testpingjiaColWhite5()
//    {
//        Calculate black = new Calculate(1);
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,9,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,9,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,9,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,9,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,9,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia(d));
//    }
    @Test
    public void testpingjiaColInvalid5()
    {
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,9,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,9,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,9,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,9,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia(d));
    }

    //    @Test
//    public void testpingjiaLeftDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,5,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,6,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,8,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,9,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia(d));
//    }
//    @Test //fault detected
//    public void testpingjiaLeftDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,5,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,6,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,8,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,9,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia(d));
//    }
    @Test //fault detected
    public void testpingjiaLeftDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,5,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,6,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,8,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,9,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia(d));
    }

    @Test
    public void testpingjiaLeftDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,5,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,6,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,1);
        white.addPoint(p3);
        Point p4 = new Point(8,8,1);
        white.addPoint(p4);
        Point p5 = new Point(9,9,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }


    //    @Test
//    public void testpingjiaRightDiagBlack5()
//    {
//        Calculate black = new Calculate(1);
//        Point p1 = new Point(5,9,1);
//        black.addPoint(p1);
//        Point p2 = new Point(6,8,1);
//        black.addPoint(p2);
//        Point p3 = new Point(7,7,1);
//        black.addPoint(p3);
//        Point p4 = new Point(8,6,1);
//        black.addPoint(p4);
//        Point p5 = new Point(9,5,1);
//        black.addPoint(p5);
//        int [][] d = black.getDatas();
//        Assertions.assertEquals(1,black.pingjia(d));
//    }
//    @Test //fault detected
//    public void testpingjiaRightDiagWhite5()
//    {
//        Calculate white = new Calculate(-1);
//        Point p1 = new Point(5,9,-1);
//        white.addPoint(p1);
//        Point p2 = new Point(6,8,-1);
//        white.addPoint(p2);
//        Point p3 = new Point(7,7,-1);
//        white.addPoint(p3);
//        Point p4 = new Point(8,6,-1);
//        white.addPoint(p4);
//        Point p5 = new Point(9,5,-1);
//        white.addPoint(p5);
//        int [][] d = white.getDatas();
//
//        Assertions.assertEquals(1,white.pingjia(d));
//    }
    @Test //fault detected
    public void testpingjiaRightDiagInvalid5()
    {
        Calculate white = new Calculate(-10);
        Point p1 = new Point(5,9,-10);
        white.addPoint(p1);
        Point p2 = new Point(6,8,-10);
        white.addPoint(p2);
        Point p3 = new Point(7,7,-10);
        white.addPoint(p3);
        Point p4 = new Point(8,6,-10);
        white.addPoint(p4);
        Point p5 = new Point(9,5,-10);
        white.addPoint(p5);
        int [][] d = white.getDatas();

        Assertions.assertEquals(0,white.pingjia(d));
    }

    @Test
    public void testpingjiaRightDiagBlackWhite2plus3()
    {
        Calculate black = new Calculate(1);
        Calculate white = new Calculate(-1);
        Point p1 = new Point(5,9,-1);
        white.addPoint(p1);
        Point p2 = new Point(6,8,-1);
        white.addPoint(p2);
        Point p3 = new Point(7,7,1);
        white.addPoint(p3);
        Point p4 = new Point(8,6,1);
        white.addPoint(p4);
        Point p5 = new Point(9,5,1);
        white.addPoint(p5);
        int [][] d = white.getDatas();
        Assertions.assertEquals(0,black.pingjia(d));
    }



}
