package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;

/**
 * Tests the methods of the java class QuickSorter.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class QuickSorterTest {
	
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
	
	/** QuickSorter that sorts students with the pivot being the first index of the array. */
	private QuickSorter<Student> firstElementSelector;
	/** QuickSorter that sorts students with the pivot being the last index of the array. */
	private QuickSorter<Student> lastElementSelector;
	/** QuickSorter that sorts students with the pivot being the middle index of the array. */
	private QuickSorter<Student> middleElementSelector;
	/** QuickSorter that sorts students with the pivot being a random index of the array. */
	private QuickSorter<Student> randomElementSelector;
	
	/**
	 * Initializes each sorter for testing.
	 */
	@Before 
	public void setUp() {
		firstElementSelector = new QuickSorter<Student>(new StudentIDComparator(), new FirstElementSelector());
		lastElementSelector = new QuickSorter<Student>(new StudentGPAComparator(), new LastElementSelector());
		middleElementSelector = new QuickSorter<Student>(new MiddleElementSelector());
		randomElementSelector = new QuickSorter<Student>();
	}
	
	/**
	 * Tests sorting students' by their ID's with QuickSorter
	 * that selects its pivot as the first index of the array.
	 */
	@Test
	public void testFirstElementSelector() {
		firstElementSelector.sort(studentsAscending);
		assertEquals(student2, studentsAscending[0]);
		assertEquals(student4, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student1, studentsAscending[3]);
		assertEquals(student3, studentsAscending[4]);
		
		firstElementSelector.sort(studentsDescending);
		assertEquals(student2, studentsDescending[0]);
		assertEquals(student4, studentsDescending[1]);
		assertEquals(student5, studentsDescending[2]);
		assertEquals(student1, studentsDescending[3]);
		assertEquals(student3, studentsDescending[4]);
		
		firstElementSelector.sort(studentsRandom);
		assertEquals(student2, studentsRandom[0]);
		assertEquals(student4, studentsRandom[1]);
		assertEquals(student5, studentsRandom[2]);
		assertEquals(student1, studentsRandom[3]);
		assertEquals(student3, studentsRandom[4]);
	}
	
	/**
	 * Tests sorting students' by their GPA with QuickSorter
	 * that selects its pivot as the last index of the array.
	 */
	@Test
	public void testLastElementSelector() {
		lastElementSelector.sort(studentsAscending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		lastElementSelector.sort(studentsDescending);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		lastElementSelector.sort(studentsRandom);
		assertEquals(student3, studentsAscending[0]);
		assertEquals(student1, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
	
	/**
	 * Tests sorting students' by their names with QuickSorter
	 * that selects its pivot as the middle index of the array.
	 */
	@Test
	public void testMiddleElementSelector() {
		middleElementSelector.sort(studentsAscending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		middleElementSelector.sort(studentsDescending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		middleElementSelector.sort(studentsRandom);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
	
	/**
	 * Tests sorting students' by their names with QuickSorter
	 * that selects its pivot as a random index in the array.
	 */
	@Test
	public void testRandomElementSelector() {
		randomElementSelector.sort(studentsAscending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		randomElementSelector.sort(studentsDescending);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
		
		randomElementSelector.sort(studentsRandom);
		assertEquals(student1, studentsAscending[0]);
		assertEquals(student3, studentsAscending[1]);
		assertEquals(student5, studentsAscending[2]);
		assertEquals(student4, studentsAscending[3]);
		assertEquals(student2, studentsAscending[4]);
	}
}
