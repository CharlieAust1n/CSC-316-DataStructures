package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class which handles testing the methods of the Java Class Student.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class StudentTest {

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
	private Student sOneEqual;
	/** Student object created for testing purposes. */
	private Student sSameFirstAsOne;

	/**
	 * Populates each student object with unique data.
	 */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sOneEqual = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sSameFirstAsOne = new Student("OneFirst", "SixLast", 6, 6, 6.0, "sixUnityID");
		
	}

	/**
	 * Tests setting and getting the first name of a student.
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Tests setting and getting the last name of a student.
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Tests setting and getting the id of a student.
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * Tests setting and getting the gpa of a student.
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Tests setting and getting the unity id of a student.
	 */
	@Test
	public void testSetUnityID() {
		sOne.setUnityID("oneUnity");
		assertEquals("oneUnity", sOne.getUnityID());
	}

	/**
	 * Tests the comparison method of two student objects.
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertTrue(sOne.compareTo(sOne) == 0);
		assertTrue(sTwo.compareTo(sTwo) == 0);
	}
	
	/**
	 * Tests the method which checks if two student objects are
	 * considered equal.
	 */
	@Test
	public void testEquals() {
		assertFalse(sOne.equals(sTwo));
		assertTrue(sOne.equals(sOneEqual));
		assertFalse(sOne.equals(sSameFirstAsOne));
		assertFalse(sThree.equals(sFour));
		assertFalse(sFour.equals(sFive));
	}
	
	/**
	 * Tests setting and getting the credit hours of a student.
	 */
	@Test
	public void testSetCreditHourse() {
		sOne.setCreditHours(15);
		assertEquals(15, sOne.getCreditHours());
	}
	
	/**
	 * Tests the outputting of student information as a 
	 * readable string of information.
	 */
	@Test
	public void testToString() {
		assertEquals("Student [first=OneFirst, last=OneLast, id=1, creditHours=1, gpa=1.0, unityID=oneUnityID]",
					sOne.toString());
		assertEquals("Student [first=FiveFirst, last=FiveLast, id=5, creditHours=5, gpa=5.0, unityID=fiveUnityID]",
					sFive.toString());
	}
	
	/**
	 * Tests the creation of unique hash code for students.
	 */
	@Test
	public void testHashCode() {
		assertEquals(1414505017, sOne.hashCode());
	}
}
