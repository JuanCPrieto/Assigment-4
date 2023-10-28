package Default;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/**
 * This class contains JUnit test cases for testing the functionality of the
 * CourseDBManager class, which manages a database of course information.
 * It ensures that the CourseDBManager class behaves as expected by testing
 * various methods and their outcomes.
 */
public class CourseDBManagerStudentTest {
    private CourseDBManager courseDBManager;

    /**
     * Initializes a new CourseDBManager object before each test case.
     */
    @Before
    public void setUp() {
        // Initialize a new CourseDBManager before each test
        courseDBManager = new CourseDBManager();
    }

    /**
     * Tests the add method of CourseDBManager.
     * It adds a course and verifies that it can be retrieved with the expected
     * attributes.
     */
    @Test
    public void testAdd() {
        // Add a course and check if it can be retrieved
        courseDBManager.add("CSCI101", 12345, 3, "123", "John Doe");
        CourseDBElement course = courseDBManager.get(12345);
        assertNotNull(course);
        assertEquals("CSCI101", course.getID());
        assertEquals(3, course.getCredits());
        assertEquals("123", course.getRoomNum());
        assertEquals("John Doe", course.getInstructorName());
    }

    /**
     * Tests the readFile method of CourseDBManager.
     * It creates a temporary file with sample course data, reads the data
     * into the database, and checks if the added courses match the expected values.
     */
    @Test
    public void testReadFile() {
        // Create a temporary file with sample course data
        try {
            File tempFile = File.createTempFile("testfile", ".txt");
            PrintWriter writer = new PrintWriter(tempFile);
            writer.println("MATH101 11111 4 202 Jane Smith");
            writer.println("HIST202 22222 3 303 John Brown");
            writer.close();

            // Read from the temporary file and check if courses were added
            courseDBManager.readFile(tempFile);
            CourseDBElement course1 = courseDBManager.get(11111);
            CourseDBElement course2 = courseDBManager.get(22222);

            assertNotNull(course1);
            assertNotNull(course2);
            assertEquals("MATH101", course1.getID());
            assertEquals(4, course1.getCredits());
            assertEquals("202", course1.getRoomNum());
            assertEquals("Jane Smith", course1.getInstructorName());

            assertEquals("HIST202", course2.getID());
            assertEquals(3, course2.getCredits());
            assertEquals("303", course2.getRoomNum());
            assertEquals("John Brown", course2.getInstructorName());

            // Delete the temporary file
            tempFile.delete();
        } catch (IOException e) {
            fail("IOException occurred during test.");
        }
    }

    /**
     * Tests the showAll method of CourseDBManager.
     * It adds some courses and checks if the showAll method returns a
     * non-empty list.
     */
    @Test
    public void testShowAll() {
        // Add some courses and check if showAll returns a non-empty list
        courseDBManager.add("CSCI101", 12345, 3, "123", "John Doe");
        courseDBManager.add("MATH202", 54321, 4, "456", "Jane Smith");
        courseDBManager.add("HIST101", 98765, 3, "789", "Bob Johnson");

        ArrayList<String> courseList = courseDBManager.showAll();
        assertFalse(courseList.isEmpty());
    }
}
