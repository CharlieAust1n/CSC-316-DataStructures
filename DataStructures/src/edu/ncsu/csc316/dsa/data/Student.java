package edu.ncsu.csc316.dsa.data;

import java.util.Objects;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Charlie Austin (cjausti2)
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	private String first;
	private String last;
	private int id;
	private int creditHours;
	private double gpa;
	private String unityID;
	
	/**
	 * Constructs a student object with given parameters.
	 * @param first Student's first name.
	 * @param last Student's last name.
	 * @param id Student's id number.
	 * @param creditHourse Number of credit hours student is taking.
	 * @param gpa Student's grade point average.
	 * @param unityID Student's unity id.
	 */
	public Student(String first, String last, int id, int creditHourse, double gpa, String unityID) {
		super();
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHourse;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Retrieves the first name of a student.
	 * @return the student's first name.
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Allows the user to set the first name of a student.
	 * @param first Student's first name
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Retrieves the last name of a student.
	 * @return the student's last name.
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Allows the user to set the last name of a student.
	 * @param last Student's last name
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Retrieves the id of a student.
	 * @return the student's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Allows the user to set the id of a student.
	 * @param id Student's id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the number of credit hours that the student is taking.
	 * @return the student's credit hours.
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Allows the user to set the number of credit hours a student is taking.
	 * @param creditHourse Student's credit hours.
	 */
	public void setCreditHours(int creditHourse) {
		this.creditHours = creditHourse;
	}

	/**
	 * Retrieves the gpa of a student.
	 * @return the student's gpa.
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Allows the user to set the gpa of a student.
	 * @param gpa the student's gpa.
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Retrieves the unity id of a student.
	 * @return the student's unity id.
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Allows the user to set the unity id of a student.
	 * @param unityID Student's unity id
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Generates a unique hash code unique to each student.
	 * @return unique hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(creditHours, first, gpa, id, last, unityID);
	}

	/**
	 * Method to check whether another instance of a student
	 * is the same as this instance of the student.
	 * @return true if they are equal. False otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return creditHours == other.creditHours && Objects.equals(first, other.first)
				&& Double.doubleToLongBits(gpa) == Double.doubleToLongBits(other.gpa) && id == other.id
				&& Objects.equals(last, other.last) && Objects.equals(unityID, other.unityID);
	}
	
	/**
	 * Method to compare two different students to sort students in order.
	 * @param other Other student object which we are comparing to.
	 * @return the comparison number of last names when the last names are not the same. If they are the same, 
	 * it checks the students' first names and if they are different, then it returns the comparison result of the first
	 * names. Else, it returns the comparison result of the twos ids.
	 */
	public int compareTo(Student other) {
		 // Compare last names
	    int lastNameComparison = this.last.compareTo(other.last);
	    if (lastNameComparison != 0) {
	        return lastNameComparison;
	    }

	    // Last names are equal, compare first names
	    int firstNameComparison = this.first.compareTo(other.first);
	    if (firstNameComparison != 0) {
	        return firstNameComparison;
	    }

	    // First names are equal, compare Student ID
	    return Integer.compare(this.id, other.id);
	}

	/**
	 * Method which returns the information of a student as a readable string.
	 * @return readable string of student information.
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}	
}
