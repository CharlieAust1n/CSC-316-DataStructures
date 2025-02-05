package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/**
 * Class which handles the testing of the creation of 
 * the class which conducts all the operations.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class StudentManagerTest {

	/** Manager object used for testing */
	private StudentManager sm;
	/** Manager object used for testing */
	private StudentManager sm2;
	/** Manager object used for testing */
	private StudentManager sm3;
	
	/**
	 * Initializes the manager objects used for testing through different comparators. 
	 * (Regular last name ordering, id comparing, and gpa comparing).
	 */
	@Before
	public void setUp() {
		sm = new StudentManager("input/student_ascendingID.csv");
		sm2 = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<Student>(new StudentIDComparator()));
		sm3 = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<Student>(new StudentGPAComparator()));
	}
	
	/**
	 * Tests the sorting of a file of students by their last names.
	 */
	@Test
	public void testSort() {
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/**
	 * Tests the sorting of a file of students by their ids.
	 */
	@Test
	public void testSortByID() {
		Student[] sorted = sm2.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Ara", sorted[1].getFirst());
		assertEquals("Lacie", sorted[2].getFirst());
		assertEquals("Idalia", sorted[3].getFirst());
		assertEquals("Evelin", sorted[4].getFirst());
		assertEquals("Lewis", sorted[5].getFirst());
		assertEquals("Alicia", sorted[6].getFirst());
		assertEquals("Tyree", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Roxann", sorted[9].getFirst());
		assertEquals("Nichole", sorted[10].getFirst());
		assertEquals("Charlene", sorted[11].getFirst());
		assertEquals("Shanti", sorted[12].getFirst());
		assertEquals("Cristine", sorted[13].getFirst());
		assertEquals("Tanner", sorted[14].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
	
	/**
	 * Tests the sorting of a file of students by the gpas.
	 */
	@Test
	public void testSortByGPA() {
		Student[] sorted = sm3.sort();
		assertEquals("Nichole", sorted[0].getFirst());
		assertEquals("Alicia", sorted[1].getFirst());
		assertEquals("Charlene", sorted[2].getFirst());
		assertEquals("Cristine", sorted[3].getFirst());
		assertEquals("Dante", sorted[4].getFirst());
		assertEquals("Lacie", sorted[5].getFirst());
		assertEquals("Idalia", sorted[6].getFirst());
		assertEquals("Ara", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Tanner", sorted[9].getFirst());
		assertEquals("Amber", sorted[10].getFirst());
		assertEquals("Roxann", sorted[11].getFirst());
		assertEquals("Tyree", sorted[12].getFirst());
		assertEquals("Evelin", sorted[13].getFirst());
		assertEquals("Shanti", sorted[14].getFirst());
		assertEquals("Lewis", sorted[15].getFirst());
	}
}
