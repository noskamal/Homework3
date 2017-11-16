package core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

public class TestAdmin {
	private IAdmin admin;
	private Student student;
	
	public TestAdmin() {
		// TODO Auto-generated constructor stub
	}
	
	 @Before
	 public void setup() {
		 this.admin = new Admin();
		 this.student = new Student();
	 }
	
    @Test
    public void testMakeClass1() {
    		//year not in the past
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }//success

    @Test
    public void testMakeClass2() {
    		//year in the past
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }//fail
    
    @Test
    public void testMakeClass3() {
    		//capacity of class > 0
        this.admin.createClass("Test", 2018, "Instructor", -1);
        assertFalse(this.admin.classExists("Test", 2018));
    }//success
    
    @Test
    public void testMakeClass4() {
    		//capacity of class > 0
        this.admin.createClass("Test", 2018, "Instructor", 0);
        assertFalse(this.admin.classExists("Test", 2018));
    }//fail
    
    @Test
    public void testMakeClass5() {
    		//no instructor can be assigned to more than two courses in a year
        this.admin.createClass("Test1", 2018, "Instructor", 11);
        this.admin.createClass("Test2", 2018, "Instructor", 12);
        assertTrue(this.admin.classExists("Test2", 2018));
    }//success
    
    
    @Test
    public void testMakeClass6() {
    		//no instructor can be assigned to more than two courses in a year
        this.admin.createClass("Test1", 2018, "Instructor", 11);
        this.admin.createClass("Test2", 2018, "Instructor", 12);
        this.admin.createClass("Test3", 2018, "Instructor", 12);
        assertFalse(this.admin.classExists("Test3", 2018));
    }//fail
    
    @Test
    public void testMakeClass7() {
    		//The className/year pair must be unique
        this.admin.createClass("Test", 2018, "Instructor1", 11);
        this.admin.createClass("Test", 2018, "Instructor2", 12);
        assertEquals(11, this.admin.getClassCapacity("Test", 2018));
        assertEquals("Instructor1", this.admin.getClassInstructor("Test", 2018));
    }//fail
    
    @Test
    public void testCapacity1() {
    		//New capacity of this class, must be at least equal to the number of students enrolled
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.student.registerForClass("Student1","Test", 2017);
        this.student.registerForClass("Student2","Test", 2017);
        this.student.registerForClass("Student3","Test", 2017);
        this.student.registerForClass("Student4","Test", 2017);
        this.admin.changeCapacity("Test", 2017, 4);
        assertEquals(4, this.admin.getClassCapacity("Test", 2017));
    }//success
    
    @Test
    public void testCapacity2() {
    		//New capacity of this class, must be at least equal to the number of students enrolled
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.student.registerForClass("Student1","Test", 2017);
        this.student.registerForClass("Student2","Test", 2017);
        this.student.registerForClass("Student3","Test", 2017);
        this.student.registerForClass("Student4","Test", 2017);
        this.admin.changeCapacity("Test", 2017, 3);
        assertEquals(10, this.admin.getClassCapacity("Test", 2017));
    }//fail
    
    @Test
    public void testCapacity3() {
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.student.registerForClass("Student1","Test", 2017);
        this.student.registerForClass("Student2","Test", 2017);
        this.student.registerForClass("Student3","Test", 2017);
        this.student.registerForClass("Student4","Test", 2017);
        this.admin.changeCapacity("Test", 2017, 0);
        assertEquals(10, this.admin.getClassCapacity("Test", 2017));
    }//fail
 
    @Test
    public void testCapacity4() {
    		//capacity: 10, new capacity: -1
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.student.registerForClass("Student1","Test", 2017);
        this.student.registerForClass("Student2","Test", 2017);
        this.student.registerForClass("Student3","Test", 2017);
        this.student.registerForClass("Student4","Test", 2017);
        this.admin.changeCapacity("Test", 2017, -1);
        assertEquals(10, this.admin.getClassCapacity("Test", 2017));
    }//fail
    
    @Test
    public void testCapacity5() {
    		//capacity: 10, new capacity: 11
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.student.registerForClass("Student1","Test", 2017);
        this.student.registerForClass("Student2","Test", 2017);
        this.student.registerForClass("Student3","Test", 2017);
        this.student.registerForClass("Student4","Test", 2017);
        this.admin.changeCapacity("Test", 2017, 11);
        assertEquals(11, this.admin.getClassCapacity("Test", 2017));
    }//success
    
    @Test
    public void testCapacity6() {
        this.admin.createClass("Test", 2017, "Instructor", 10);
        this.admin.changeCapacity("Test", 2017, 3);
        assertEquals(3, this.admin.getClassCapacity("Test", 2017));
    }//success

}
