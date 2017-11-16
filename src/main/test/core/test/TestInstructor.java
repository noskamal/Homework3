package core.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import core.api.IAdmin;
import core.api.impl.Admin;
import core.api.impl.Instructor;
import core.api.impl.Student;

public class TestInstructor {
	private IAdmin admin;
	private Student student;
	private Instructor instructor; 

	public TestInstructor() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	 public void setup() {
		 this.admin = new Admin();
		 this.student = new Student();
		 this.instructor = new Instructor();
	 }
	
 	@Test
    public void testAddHomework1() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
        assertTrue(this.instructor.homeworkExists("Test", 2017, "Homework"));
    }//success
    
    @Test
    public void testAddHomework2() {
    		//add homework, provided this instructor has been assigned to this class
    		this.admin.createClass("Test", 2017, "Instructor", 10);
        this.instructor.addHomework("Instructor1", "Test", 2017, "Homework");
        assertFalse(this.instructor.homeworkExists("Test", 2017, "Homework"));
    }//fail
    
    @Test
    public void testAddHomework3() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
        this.instructor.addHomework("Instructor", "Test1", 2017, "Homework");
        assertFalse(this.instructor.homeworkExists("Test", 2017, "Homework"));
        assertFalse(this.instructor.homeworkExists("Test1", 2017, "Homework"));
    }//fail
    
    @Test
    public void testAddHomework4() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
        this.instructor.addHomework("Instructor", "Test", 2018, "Homework");
        assertFalse(this.instructor.homeworkExists("Test", 2018, "Homework"));
        assertFalse(this.instructor.homeworkExists("Test", 2017, "Homework"));
    }//fail
    
    @Test
    public void testAssignGrade1() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//success
    
    @Test
    public void testAssignGrade2() {
    		//instructor not assigned to the class
    		this.admin.createClass("Test", 2017, "Instructor1", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail
    
    @Test
    public void testAssignGrade3() {
    		//homework not assigned
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework1");
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail
    
    @Test
    public void testAssignGrade4() {
    		//student has not submitted homework
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
        this.student.submitHomework("Student", "Homework1", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail
    
    @Test
    public void testAssignGrade5() {
    		//homework not assigned
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail
    
    @Test
    public void testAssignGrade6() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
    		this.student.isRegisteredFor("Student", "Test", 2017);
    		this.instructor.addHomework("Instructor1", "Test", 2017, "Homework1");
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail
    
    @Test
    public void testAssignGrade7() {
    		this.admin.createClass("Test", 2017, "Instructor", 10);
        this.instructor.addHomework("Instructor", "Test", 2017, "Homework");
        this.student.submitHomework("Student", "Homework", "Solution", "Test", 2017);
        this.instructor.assignGrade("Instructor", "Test", 2017, "Homework", "Student", 100);
        assertNotEquals(new Integer(100), this.instructor.getGrade("Test", 2017, "Homework", "Student"));
    }//fail

}
