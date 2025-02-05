/**
 * 
 */
package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Class which handles testing the methods of the Java Class RadixSorter
 * 
 * @author Charlie Austin (cjausti2)
 */
public class RadixSorterTest {

	/** Student object created for testing purposes. */
	private Student sOne;
	/** Student object created for testing purposes. */
	private Student sTwo;
	/** Student object created for testing purposes. */
	private Student sThree;
	/** Student object created for testing purposes. */
	private Student sFour;
	/** Student object created for testing purposes. */
	private Student sFive;
	/** Student object created for testing purposes. */
	
	/** Radix sorter of student objects created for testing purposes. */
	private RadixSorter<Student> sorter;
	
	/**
	 * Populates each student object with unique data. 
	 * Initializes the radix sorter.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}
	
	/**
	 * Tests sorting students in order based of their ids.
	 */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}

}
