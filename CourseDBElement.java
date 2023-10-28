package Default;
import java.util.Objects;

/**
 * This class represents an element in a course database. It stores information about a course,
 * including the course ID, CRN (Course Registration Number), credits, room number, and instructor name.
 * It implements the Comparable interface for comparing elements based on CRN.
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
    private String courseId;       // The course ID.
    private int crn;               // The Course Registration Number.
    private int credits;           // The number of credits for the course.
    private String roomNumber;     // The room number where the course is held.
    private String instructorName; // The name of the instructor for the course.

    /**
     * Constructs a CourseDBElement object with specified attributes.
     *
     * @param courseId       The course ID.
     * @param crn            The Course Registration Number (CRN).
     * @param credits        The number of credits for the course.
     * @param roomNumber     The room number where the course is held.
     * @param instructorName The name of the instructor for the course.
     */
    public CourseDBElement(String courseId, int crn, int credits, String roomNumber, String instructorName) {
        this.courseId = courseId;
        this.crn = crn;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructorName = instructorName;
    }

    /**
     * Default constructor for CourseDBElement. Initializes attributes to default values.
     */
    public CourseDBElement() {
        this.courseId = null;
        this.crn = 0;
        this.credits = 0;
        this.roomNumber = null;
        this.instructorName = null;
    }

    /**
     * Gets the course ID.
     *
     * @return The course ID.
     */
    public String getID() {
        return courseId;
    }

    /**
     * Gets the CRN (Course Registration Number).
     *
     * @return The CRN.
     */
    public int getCRN() {
        return crn;
    }

    /**
     * Gets the number of credits for the course.
     *
     * @return The number of credits.
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Gets the room number where the course is held.
     *
     * @return The room number.
     */
    public String getRoomNum() {
        return roomNumber;
    }

    /**
     * Gets the name of the instructor for the course.
     *
     * @return The instructor's name.
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * Compares two CourseDBElement objects based on their CRN (Course Registration Number).
     *
     * @param other The CourseDBElement to compare with.
     * @return A negative integer if this CRN is less than other's CRN,
     *         zero if they are equal, or a positive integer if this CRN is greater.
     */
    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn);
    }

    /**
     * Generates a hash code based on the CRN.
     *
     * @return The hash code based on the CRN.
     */
    @Override
    public int hashCode() {
        return Objects.hash(crn);
    }

    /**
     * Sets the CRN (Course Registration Number) for the CourseDBElement.
     *
     * @param parseInt The new CRN to set.
     */
    public void setCRN(int parseInt) {
        crn = parseInt;
    }
}
