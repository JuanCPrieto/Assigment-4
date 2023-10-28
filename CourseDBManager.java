package Default;
import java.io.*;
import java.util.*;

/**
 * The CourseDBManager class manages a course database and provides functionality
 * to add, retrieve, and display course information. It also supports reading
 * course data from a file.
 */
public class CourseDBManager implements CourseDBManagerInterface {
    private CourseDBStructure courseDB;

    /**
     * Initializes the CourseDBManager with an empty course database.
     */
    public CourseDBManager() {
        // Initialize the course database structure
        courseDB = new CourseDBStructure(500); // You can adjust the size as needed
    }

    /**
     * Adds a new course to the database.
     *
     * @param id         The course ID.
     * @param crn        The Course Registration Number (CRN).
     * @param credits    The number of credits for the course.
     * @param roomNum    The room number where the course is held.
     * @param instructor The name of the instructor for the course.
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement course = new CourseDBElement(id, crn, credits, roomNum, instructor);
        courseDB.add(course);
    }

    /**
     * Retrieves a course by its Course Registration Number (CRN).
     *
     * @param crn The CRN of the course to retrieve.
     * @return The course information as a CourseDBElement object.
     *         If the course is not found, an error message is printed, and null is returned.
     */
    @Override
    public CourseDBElement get(int crn) {
        try {
            return courseDB.get(crn);
        } catch (Exception e) {
            // Handle the exception as needed
            System.err.println("Error: " + e.getMessage());
            return null; // or throw a custom exception, log, or handle the error appropriately
        }
    }

    /**
     * Reads course information from a file and populates the course database.
     *
     * @param input The file containing course data to be read.
     * @throws FileNotFoundException If the input file is not found.
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");
                if (data.length >= 5) {
                    String id = data[0];
                    int crn = Integer.parseInt(data[1]);
                    int credits = Integer.parseInt(data[2]);
                    String roomNum = data[3];
                    String instructor = data[4];
                    add(id, crn, credits, roomNum, instructor);
                }
            }
        }
    }

    /**
     * Retrieves and returns a list of all courses as strings.
     *
     * @return A list of course information as strings.
     */
    @Override
    public ArrayList<String> showAll() {
        return courseDB.showAll();
    }

    /**
     * The main method that serves as the entry point of the program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        CourseDBManager courseDBManager = new CourseDBManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to read from a file, 2 to add a course, 3 to display all courses, or 4 to exit: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the file path: ");
                    String filePath = scanner.next();
                    File inputFile = new File(filePath);
                    try {
                        courseDBManager.readFile(inputFile);
                        System.out.println("Courses read from the file.");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter course ID: ");
                    String id = scanner.next();
                    System.out.print("Enter CRN: ");
                    int crn = scanner.nextInt();
                    System.out.print("Enter credits: ");
                    int credits = scanner.nextInt();
                    System.out.print("Enter room number: ");
                    String roomNum = scanner.next();
                    System.out.print("Enter instructor: ");
                    String instructor = scanner.next();
                    courseDBManager.add(id, crn, credits, roomNum, instructor);
                    break;
                case 3:
                    ArrayList<String> courseList = courseDBManager.showAll();
                    for (String courseString : courseList) {
                        System.out.println(courseString);
                    }
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
