package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Class which handles testing methods from the Java Class InsertionSorter.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class InsertionSorterTest {
	/** Array of numbers ordered in ascending order. */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** Array of numbers ordered in descending order. */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/* Array of numbers in random order. */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
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

	/** Array of student objects ordered in ascending order based of their ids. */
	private Student[] studentsAscending = {student2, student4, student5, student1, student3};
	/** Array of student objects ordered in descending order based of their ids. */
	private Student[] studentsDescending = {student3, student1, student5, student4, student2};
	/** Array of student objects in random order. */
	private Student[] studentsRandom = {student1, student2, student3, student4, student5};
	
	/** Insertion sorter of intergers created for testing purposes */
	private InsertionSorter<Integer> integerSorter;
	/** Insertion sorter of students created for testing purposes */
	private InsertionSorter<Student> studentSorter;
	/** Sorter of students created for testing purposes */
	private Sorter<Student> studentIDSorter;
	/** Sorter of students created for testing purposes */
	private Sorter<Student> studentGPASorter;

	/**
	 * Initializes the sorters to custom comparators. 
	 */
	@Before
	public void setUp() {
		integerSorter = new InsertionSorter<Integer>();
		studentSorter = new InsertionSorter<Student>();
		studentIDSorter = new InsertionSorter<Student>(new StudentIDComparator());
		studentGPASorter = new InsertionSorter<Student>(new StudentGPAComparator());
	}

	/**
	 * Tests sorting integers in ordered fashion (ascending order)
	 */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(1, (int) dataAscending[0]);
		assertEquals(2, (int) dataAscending[1]);
		assertEquals(3, (int) dataAscending[2]);
		assertEquals(4, (int) dataAscending[3]);
		assertEquals(5, (int) dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(1, (int) dataDescending[0]);
		assertEquals(2, (int) dataDescending[1]);
		assertEquals(3, (int) dataDescending[2]);
		assertEquals(4, (int) dataDescending[3]);
		assertEquals(5, (int) dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(1, (int) dataRandom[0]);
		assertEquals(2, (int) dataRandom[1]);
		assertEquals(3, (int) dataRandom[2]);
		assertEquals(4, (int) dataRandom[3]);
		assertEquals(5, (int) dataRandom[4]);
	}

	/**
	 * Tests sorting students in ordered fashion (ascending order based of names).
	 */
	@Test
	public void testSortStudent() {
		studentSorter.sort(studentsAscending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		studentSorter.sort(studentsDescending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		studentSorter.sort(studentsRandom);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
	
	/**
	 * Tests sorting students in ordered fashion (ascending order based of ids).
	 */
	@Test
	public void testSortStudentByID() {
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
	 * Tests sorting students in ordered fashion (ascending order based of gpas).
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
