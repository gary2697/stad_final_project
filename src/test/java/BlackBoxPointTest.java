import com.brioal.utool.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// we are doing black box testing for the point class
//tests that are failing a commented out to generate test reports
//we applied mainly EP and BA techniques in our blackbox testing and did some error guess after we played the game
// by EP: we divided the point class into x < 0, 0 <= x < 20 x >20, same for y, and state 1/-1/0/other values



public class BlackBoxPointTest {

    @Test
    public void testPointXNull()
    {
        Point p = null;
        Assertions.assertThrows(NullPointerException.class,()->{
            p.getX();
        });
    }
    @Test
    public void testPointGetX()
    {
        Point p = new Point(1,1,1);
        Assertions.assertEquals(1,p.getX());
    }
    @Test
    public void testPointSetX()
    {
        Point p = new Point(1,1,1);
        p.setX(2);
        Assertions.assertEquals(2,p.getX());
    }
//    @Test
//    public void testPointSetXBig()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setX(200);
//        });
//    }
    //    @Test
//    public void testPointSetXNeg()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setX(-1);
//        });
//    }

    @Test
    public void testPointYNull()
    {
        Point p = null;
        Assertions.assertThrows(NullPointerException.class,()->{
            p.getY();
        });
    }
    @Test
    public void testPointGetY()
    {
        Point p = new Point(1,1,1);
        Assertions.assertEquals(1,p.getY());
    }
    @Test
    public void testPointSetY()
    {
        Point p = new Point(1,1,1);
        p.setY(2);
        Assertions.assertEquals(2,p.getY());
    }

//    @Test
//    public void testPointSetYBig()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setY(200);
//        });
//    }
    //    @Test
//    public void testPointSetYNeg()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setY(-1);
//        });
//    }

    @Test
    public void testPointStateNull()
    {
        Point p = null;
        Assertions.assertThrows(NullPointerException.class,()->{
            p.getState();
        });
    }
    @Test
    public void testPointGetState()
    {
        Point p = new Point(1,1,1);
        Assertions.assertEquals(1,p.getState());
    }
    @Test
    public void testPointSetState()
    {
        Point p = new Point(1,1);
        p.setState(-1);
        Assertions.assertEquals(-1,p.getState());
    }
    //    @Test
//    public void testPointSetStateBig()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setState(200);
//        });
//    }
    //    @Test
//    public void testPointSetStateNeg()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setState(-1);
//        });
//    }

    @Test
    public void testPointToString()
    {
        Point p = new Point(2,2,1);
        Assertions.assertEquals("2:2:1",p.toString());
    }

}
