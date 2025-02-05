package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Class which tests reading a file and storing the information
 * from the file into a student object.
 * 
 * @author Charlie Austin (cjausti2)
 */
public class StudentReaderTest {
	
	/**
	 * Tests reading a file and getting inserting 
	 * the data into a list of students.
	 */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/**
	 * Tests reading a file and storing data which has students ordered
	 * in descending order.
	 */
	@Test
	public void testReadFileDescendingOrder() {
		Student[] contentsDescending = StudentReader.readInputAsArray("input/student_descendingID.csv");

		assertEquals("Dante", contentsDescending[0].getFirst());
		assertEquals("Tanner", contentsDescending[1].getFirst());
		assertEquals("Cristine", contentsDescending[2].getFirst());
		assertEquals("Shanti", contentsDescending[3].getFirst());
		assertEquals("Charlene", contentsDescending[4].getFirst());
		assertEquals("Nichole", contentsDescending[5].getFirst());
		assertEquals("Roxann", contentsDescending[6].getFirst());
		assertEquals("Loise", contentsDescending[7].getFirst());
		assertEquals("Tyree", contentsDescending[8].getFirst());
		assertEquals("Alicia", contentsDescending[9].getFirst());
		assertEquals("Lewis", contentsDescending[10].getFirst());
		assertEquals("Evelin", contentsDescending[11].getFirst());
		assertEquals("Idalia", contentsDescending[12].getFirst());
		assertEquals("Lacie", contentsDescending[13].getFirst());
		assertEquals("Ara", contentsDescending[14].getFirst());
		assertEquals("Amber", contentsDescending[15].getFirst());
	}
	
	/**
	 * Tests reading and storing data from a file that
	 * has students ranked in random order.
	 */
	@Test
	public void testReadFileDescriptionRandom() {
		Student[] contentsRandom = StudentReader.readInputAsArray("input/student_randomOrder.csv");
		
		assertEquals("Lacie", contentsRandom[0].getFirst());
		assertEquals("Tyree", contentsRandom[1].getFirst());
		assertEquals("Loise", contentsRandom[2].getFirst());
		assertEquals("Idalia", contentsRandom[3].getFirst());
		assertEquals("Shanti", contentsRandom[4].getFirst());
		assertEquals("Roxann", contentsRandom[5].getFirst());
		assertEquals("Evelin", contentsRandom[6].getFirst());
		assertEquals("Alicia", contentsRandom[7].getFirst());
		assertEquals("Charlene", contentsRandom[8].getFirst());
		assertEquals("Nichole", contentsRandom[9].getFirst());
		assertEquals("Ara", contentsRandom[10].getFirst());
		assertEquals("Dante", contentsRandom[11].getFirst());
		assertEquals("Tanner", contentsRandom[12].getFirst());
		assertEquals("Cristine", contentsRandom[13].getFirst());
		assertEquals("Amber", contentsRandom[14].getFirst());
		assertEquals("Lewis", contentsRandom[15].getFirst());
	}
}
