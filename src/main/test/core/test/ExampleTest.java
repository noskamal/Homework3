package core.test;

import core.api.IAdmin;
import core.api.IStudent; 
import core.api.IInstructor; 
import core.api.impl.Admin;
import core.api.impl.Student;
import core.api.impl.Instructor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Vincent on 23/2/2017.
 */
public class ExampleTest {

    
	private IStudent student;
	private IInstructor instructor;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student(); 
        this.instructor = new Instructor(); 
    }

}
