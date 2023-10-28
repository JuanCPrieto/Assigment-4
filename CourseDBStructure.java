package Default;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The CourseDBStructure class represents a data structure for storing course information using a hash table.
 */
public class CourseDBStructure implements CourseDBStructureInterface {
    private int tableSize;
    private LinkedList<CourseDBElement>[] hashTable;

    /**
     * Constructor 1 - Creates a CourseDBStructure instance with an estimated number of courses.
     *
     * @param estimatedNumCourses The estimated number of courses to initialize the hash table size.
     */
    public CourseDBStructure(int estimatedNumCourses) {
        // Initialize the hash table size to the next prime number greater than or equal to estimatedNumCourses.
        tableSize = getNextPrime(estimatedNumCourses);
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    /**
     * Constructor 2 (for testing) - Creates a CourseDBStructure instance with a specified size.
     *
     * @param testing A string indicating testing mode.
     * @param size    The size of the hash table to be used for testing.
     */
    public CourseDBStructure(String testing, int size) {
        // Initialize the hash table with the specified size for testing purposes.
        tableSize = size;
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    /**
     * Adds a CourseDBElement to the hash table. If an element with the same CRN already exists, it is not added again.
     *
     * @param element The CourseDBElement to add to the hash table.
     */
    @Override
    public void add(CourseDBElement element) {
        // Calculate the hash code for the CRN, determine the index, and add the element to the linked list.
        int hashCode = getHashCode(element.getCRN());
        int index = hashCode % tableSize;
        LinkedList<CourseDBElement> list = hashTable[index];
        if (list.isEmpty()) {
            list.add(element);
        } else {
            for (CourseDBElement existingElement : list) {
                if (existingElement.getCRN() == element.getCRN()) {
                    return; // Element already exists, exit quietly
                }
            }
            list.add(element);
        }
    }

    /**
     * Retrieves a CourseDBElement based on the provided CRN.
     *
     * @param crn The CRN of the course to retrieve.
     * @return The CourseDBElement associated with the given CRN.
     * @throws IOException If the course with the specified CRN is not found in the hash table.
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        // Calculate the hash code for the CRN, determine the index, and search for the element.
        int hashCode = getHashCode(crn);
        int index = hashCode % tableSize;
        LinkedList<CourseDBElement> list = hashTable[index];
        for (CourseDBElement element : list) {
            if (element.getCRN() == crn) {
                return element;
            }
        }
        throw new IOException("Course not found with CRN: " + crn);
    }

    /**
     * Returns an ArrayList of string representations of all course elements in the hash table.
     *
     * @return An ArrayList of strings representing all course elements in the hash table.
     */
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> courseStrings = new ArrayList<>();
        for (LinkedList<CourseDBElement> list : hashTable) {
            for (CourseDBElement element : list) {
                courseStrings.add(element.toString());
            }
        }
        return courseStrings;
    }

    /**
     * Returns the size of the hash table.
     *
     * @return The size of the hash table.
     */
    @Override
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Calculates the hash code for a given CRN.
     *
     * @param crn The CRN for which to calculate the hash code.
     * @return The hash code for the CRN.
     */
    private int getHashCode(int crn) {
        return Integer.toString(crn).hashCode();
    }

    /**
     * Finds the next prime number greater than or equal to the given number.
     *
     * @param n The number for which to find the next prime number.
     * @return The next prime number greater than or equal to n.
     */
    private int getNextPrime(int n) {
        if (n <= 1) {
            return 2;
        }
        int prime = n;
        boolean found = false;
        while (!found) {
            prime++;
            if (isPrime(prime)) {
                found = true;
            }
        }
        return prime;
    }

    /**
     * Checks if a number is prime.
     *
     * @param num The number to check for primality.
     * @return True if the number is prime, false otherwise.
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
