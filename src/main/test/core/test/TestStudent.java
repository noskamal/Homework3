package core.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

public class TestStudent {
	private IAdmin admin;
	private Student student;
	private Instructor instructor; 

	public TestStudent() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	 public void setup() {
		 this.admin = new Admin();
		 this.student = new Student();
		 this.instructor = new Instructor();
	 }
	
    @Test
    public void testRegisterForClass1() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.registerForClass("Student1", "Test", 2017);
    		this.student.registerForClass("Student2", "Test", 2017);
    		this.student.registerForClass("Student3", "Test", 2017);
    		assertTrue(student.isRegisteredFor("Student3", "Test", 2017));
    }//success
    
    @Test
    public void testRegisterForClass2() {
    		//class doesn't exists
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.registerForClass("Student1", "Test", 2017);
    		this.student.registerForClass("Student2", "Test", 2017);
    		this.student.registerForClass("Student3", "Test1", 2017);
    		assertFalse(student.isRegisteredFor("Student3", "Test1", 2017));
    }//fail
    
    @Test
    public void testRegisterForClass3() {
    		//class exists
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.registerForClass("Student1", "Test", 2017);
    		this.student.registerForClass("Student2", "Test", 2017);
    		this.student.registerForClass("Student3", "Test", 2018);
    		assertFalse(student.isRegisteredFor("Student3", "Test", 2018));
    }//fail
    
    @Test
    public void testRegisterForClass4() {
    		//enrollment capacity
    		this.admin.createClass("Test", 2017, "Instructor", 2);
    		this.student.registerForClass("Student1", "Test", 2017);
    		this.student.registerForClass("Student2", "Test", 2017);
    		this.student.registerForClass("Student3", "Test", 2017);
    		assertFalse(student.isRegisteredFor("Student3", "Test", 2017));
    }//fail
    
    @Test
    public void testRegisterForClass5() {
    		//enrollment capacity
    		this.admin.createClass("Test", 2017, "Instructor", -1);
    		this.student.registerForClass("Student1", "Test", 2017);
    		assertFalse(student.isRegisteredFor("Student1", "Test", 2017));
    }//fail
    
    @Test
    public void testDropClass1() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.registerForClass("Student", "Test", 2017);
    		this.student.dropClass("Student", "Test", 2017);
    		assertFalse(student.isRegisteredFor("Student", "Test", 2017));
    }//success
    
    @Test
    public void testDropClass2() {
    		//student not registered in the class
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.dropClass("Student", "Test", 2017);
    		assertFalse(student.isRegisteredFor("Student", "Test", 2017));
    }//fail
    
    @Test
    public void testDropClass3() {
    		//class ended
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.registerForClass("Student", "Test", 2016);
    		this.student.dropClass("Student", "Test", 2016);
    		assertFalse(student.isRegisteredFor("Student", "Test", 2016));
    }//fail
    
    @Test
    public void testSubmitHomework1() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
		this.student.registerForClass("Student", "Test", 2017);
		this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
		this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
		assertTrue(student.hasSubmitted("Student", "Homework", "Test", 2017));
    }//success
    
    @Test
    public void testSubmitHomework2() {
    		//homework doesn't exist
    		this.admin.createClass("Test", 2017, "Instructor", 10);
		this.student.registerForClass("Student", "Test", 2017);
		this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
		assertFalse(student.hasSubmitted("Student", "Homework", "Test", 2017));
    }//fail
    
    @Test
    public void testSubmitHomework3() {
    		//student isn't registered in the class
    		this.admin.createClass("Test", 2017, "Instructor", 10);
		this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
		this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
		assertFalse(student.hasSubmitted("Student", "Homework", "Test", 2017));
    }//fail
    
    @Test
    public void testSubmitHomework4() {
    		//class is not taught in the current year
    		this.admin.createClass("Test", 2018, "Instructor", 10);
    		this.student.registerForClass("Student", "Test", 2018);
		this.instructor.addHomework("Instructor", "Test", 2018, "Homework");
		this.student.submitHomework("Student", "Homework", "Solution", "Test", 2018);
		assertFalse(student.hasSubmitted("Student", "Homework", "Test", 2018));
    }//fail

}
