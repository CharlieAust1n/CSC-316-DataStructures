package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Class which handles testing the methods of the Java Class SelectionSorter
 * 
 * @author Charlie Austin (cjausti2)
 */
public class SelectionSorterTest {
	
	/** Unique student object created for testing purposes. */
	private Student student1 = new Student("Charlie", "Austin", 200486586, 15, 3.5, "cjausti2");
	/** Unique student object created for testing purposes. */
	private Student student2 = new Student("John", "Smith", 200431423, 15, 3.0, "jsmith1");
	/** Unique student object created for testing purposes. */
	private Student student3 = new Student("Ashely", "Grahm", 200521212, 17, 3.8, "agrahm2");
	/** Unique student object created for testing purposes. */
	private Student student4 = new Student("Eli", "Oliver", 200480718, 15, 3.1, "epoliver");
	/** Unique student object created for testing purposes. */
	private Student student5 = new Student("Johnathon", "McBride", 200483612, 15, 3.2, "jmcbride");

	/** Array of student objects in ascending order based of their ids. */
	private Student[] studentsAscending = {student2, student4, student5, student1, student3};
	/** Array of student objects in descending order based of their ids. */
	private Student[] studentsDescending = {student3, student1, student5, student4, student2};
	/** Array of students objects in random order. */
	private Student[] studentsRandom = {student1, student2, student3, student4, student5};
	
	/** Selection sorter for student objects that will be used for sorting based of ids. */
	private SelectionSorter<Student> studentIDSorter;
	/** Selection sorter for student objects that will be used for sorting based of gpas. */
	private SelectionSorter<Student> studentGPASorter;
	
	/**
	 * Initializes the two sorters to their respected comparators.
	 */
	@Before
	public void setup() {
		studentIDSorter = new SelectionSorter<Student>(new StudentIDComparator());
		studentGPASorter = new SelectionSorter<Student>(new StudentGPAComparator());
	}
	
	/**
	 * Tests sorting students by their ids. 
	 * Sorts them to be in ascending order (i.e., 1, 2, 3, ... )
	 */
	@Test
	public void testSortStudentsByID() {
		studentIDSorter.sort(studentsAscending);
		assertEquals(student2, studentsAscending[0]);
		assertEquals(student4, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student1, studentsAscending[3]);
		assertEquals(student3, studentsAscending[4]);
		
		studentIDSorter.sort(studentsDescending);
		assertEquals(student2, studentsDescending[0]);
		assertEquals(student4, studentsDescending[1]);
		assertEquals(student5, studentsDescending[2]);
		assertEquals(student1, studentsDescending[3]);
		assertEquals(student3, studentsDescending[4]);
		
		studentIDSorter.sort(studentsRandom);
		assertEquals(student2, studentsRandom[0]);
		assertEquals(student4, studentsRandom[1]);
		assertEquals(student5, studentsRandom[2]);
		assertEquals(student1, studentsRandom[3]);
		assertEquals(student3, studentsRandom[4]);
	}

	/**
	 * Tests sorting student by their gpas.
	 * Will sort to be in ascending order (i.e., 4.0, 3.0, 2.0, 1.0, ... )
	 */
	@Test
	public void testSortStudentByGPA() {
		studentGPASorter.sort(studentsAscending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		studentGPASorter.sort(studentsDescending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		studentGPASorter.sort(studentsRandom);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
}
