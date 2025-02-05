/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Class which handles the testing of methods
 * from the bubble sorter java class.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class BubbleSorterTest {
	
	/** Student object created for testing purposes */
	private Student student1 = new Student("Charlie", "Austin", 200486586, 15, 3.5, "cjausti2");
	/** Student object created for testing purposes */
	private Student student2 = new Student("John", "Smith", 200431423, 15, 3.0, "jsmith1");
	/** Student object created for testing purposes */
	private Student student3 = new Student("Ashely", "Grahm", 200521212, 17, 3.8, "agrahm2");
	/** Student object created for testing purposes */
	private Student student4 = new Student("Eli", "Oliver", 200480718, 15, 3.1, "epoliver");
	/** Student object created for testing purposes */
	private Student student5 = new Student("Johnathon", "McBride", 200483612, 15, 3.2, "jmcbride");

	/** Array of student objects ordered in ascending order based of their ids. */
	private Student[] studentsAscending = {student2, student4, student5, student1, student3};
	/** Array of student objects ordered in descending order based of their ids. */
	private Student[] studentsDescending = {student3, student1, student5, student4, student2};
	/** Array of student objected ordered in random order. */
	private Student[] studentsRandom = {student1, student2, student3, student4, student5};
	
	/** Bubble sorter used for testing purposes */
	private BubbleSorter<Student> normalSorter;
	/** Bubble sorter used for testing purposes */
	private BubbleSorter<Student> idSorter;
	/** Bubble sorter used for testing purposes */
	private BubbleSorter<Student> gpaSorter;
	
	/**
	 * Initializes each of the bubble sorters to a custom comparator. One for
	 * the regular, one for ids, and one for gpas.
	 */
	@Before
	public void setUp() {
		normalSorter = new BubbleSorter<Student>();
		idSorter = new BubbleSorter<Student>(new StudentIDComparator());
		gpaSorter = new BubbleSorter<Student>(new StudentGPAComparator());
	}
	
	/**
	 * Tests sorting students in order based of their names.
	 */
	@Test
	public void testSortStudentNormal() {
		normalSorter.sort(studentsAscending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		normalSorter.sort(studentsDescending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		normalSorter.sort(studentsRandom);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
	
	/**
	 * Tests sorting students based of their ids.
	 */
	@Test
	public void testSortStudentsByID() {
		idSorter.sort(studentsAscending);
		assertEquals(student2, studentsAscending[0]);
		assertEquals(student4, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student1, studentsAscending[3]);
		assertEquals(student3, studentsAscending[4]);
		
		idSorter.sort(studentsDescending);
		assertEquals(student2, studentsDescending[0]);
		assertEquals(student4, studentsDescending[1]);
		assertEquals(student5, studentsDescending[2]);
		assertEquals(student1, studentsDescending[3]);
		assertEquals(student3, studentsDescending[4]);
		
		idSorter.sort(studentsRandom);
		assertEquals(student2, studentsRandom[0]);
		assertEquals(student4, studentsRandom[1]);
		assertEquals(student5, studentsRandom[2]);
		assertEquals(student1, studentsRandom[3]);
		assertEquals(student3, studentsRandom[4]);
	}

	/**
	 * Tests sorting students based of their gpas.
	 */
	@Test
	public void testSortStudentByGPA() {
		gpaSorter.sort(studentsAscending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		gpaSorter.sort(studentsDescending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		gpaSorter.sort(studentsRandom);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
}
