package Default;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit tests for the CourseDBElement class. It tests the constructors,
 * setter method, compareTo method, and hashCode method of the CourseDBElement class.
 */
public class CourseDBElementStudentTest {

    /**
     * Test the default constructor of CourseDBElement.
     */
    @Test
    public void testDefaultConstructor() {
        CourseDBElement course = new CourseDBElement();
        assertNull(course.getID());
        assertEquals(0, course.getCRN());
        assertEquals(0, course.getCredits());
        assertNull(course.getRoomNum());
        assertNull(course.getInstructorName());
    }

    /**
     * Test the parameterized constructor of CourseDBElement.
     */
    @Test
    public void testParameterizedConstructor() {
        CourseDBElement course = new CourseDBElement("CS101", 12345, 3, "Room 101", "John Doe");
        assertEquals("CS101", course.getID());
        assertEquals(12345, course.getCRN());
        assertEquals(3, course.getCredits());
        assertEquals("Room 101", course.getRoomNum());
        assertEquals("John Doe", course.getInstructorName());
    }

    /**
     * Test the setCRN method of CourseDBElement.
     */
    @Test
    public void testSetCRN() {
        CourseDBElement course = new CourseDBElement();
        course.setCRN(54321);
        assertEquals(54321, course.getCRN());
    }

    /**
     * Test the compareTo method of CourseDBElement.
     */
    @Test
    public void testCompareTo() {
        CourseDBElement course1 = new CourseDBElement("CS101", 12345, 3, "Room 101", "John Doe");
        CourseDBElement course2 = new CourseDBElement("MATH101", 54321, 4, "Room 201", "Alice Smith");

        assertTrue(course1.compareTo(course2) < 0);  // Course1 CRN < Course2 CRN
        assertTrue(course2.compareTo(course1) > 0);  // Course2 CRN > Course1 CRN
        assertEquals(0, course1.compareTo(course1));   // Same CRN, so they are equal
    }

    /**
     * Test the hashCode method of CourseDBElement.
     */
    @Test
    public void testHashCode() {
        CourseDBElement course1 = new CourseDBElement("CS101", 12345, 3, "Room 101", "John Doe");
        CourseDBElement course2 = new CourseDBElement("MATH101", 54321, 4, "Room 201", "Alice Smith");

        assertNotEquals(course1.hashCode(), course2.hashCode());
        assertEquals(course1.hashCode(), course1.hashCode());  // Hash code is consistent
    }
}

