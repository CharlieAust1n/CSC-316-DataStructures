package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class which handles testing the methods of the Java Class StudentIdComparator
 * 
 * @author Charlie Austin (cjausti2)
 */
public class StudentIDComparatorTest {

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

	/** Comparator which will be used to sort students by ids. */
	private StudentIDComparator comparator;

	/**
	 * Populates each student object with unique data.
	 * Initializes the comparator.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");

		comparator = new StudentIDComparator();
	}

	/**
	 * Tests the comparator by sorting students into order
	 * by their ids.
	 */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);

		assertTrue(comparator.compare(sFour, sThree) > 0);
		assertTrue(comparator.compare(sFive, sFive) == 0);
	}
}
