import com.brioal.utool.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.brioal.utool.Calculate;
//this is our white box testing for the point class
//we aim for 100% branch coverage
//the faults we found are that the developer did not do input checking for the constructor and the setter methods
//which resulting in the users being able to set X,Y and state to some invalid values
//failure report
/*
	Setters for point should make sure that x,y is inside the range of [0,20) should throw exception otherwise
	Setters for setstate should make sure values are 1,0,-1,

 */
public class pointTest {
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
        Point p = new Point();
        p.setX(2);
        Assertions.assertEquals(2,p.getX());
    }

//    @Test
//    public void testPointSetX()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setX(200);
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
//    public void testPointSetY()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setY(200);
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
//    public void testPointSetState()
//    {
//        Point p = new Point(1,1,1);
//        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{
//            p.setState(200);
//        });
//    }
    @Test
    public void testPointToString()
    {
        Point p = new Point(2,2,1);
        Assertions.assertEquals("2:2:1",p.toString());
    }



}
