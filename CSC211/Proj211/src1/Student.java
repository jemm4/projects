import java.util.Scanner;

/**
@author	Jeffrey Martinez

Purpose:	Defines a student with full name and a complete set of grades.

Input:		Prompts the user to enter the name of the student in the format last, first and the grade. 

Output:		Displays the full name of the student in the format of first name, last name; prints out the class average
			prints out the name of the student along with the grade;
*/

public class Student {
	//declaring the variables 
	
	private Grades grade = new Grades(); // calls the Grades class to work in the Student class
	
	private Scanner keyboard; // scanner named keyboard
		
	private String fullName; // the first and last name will be stored in fullName
	
	// sets all the attributes of the student (name and grades)
	public void setup(){
		setName();
		setGrades();
	}
	
	private void setName(){
		keyboard = new Scanner(System.in); 
		System.out.println("Please enter the student's name"
				+ " in the form of 'Doe, John' or 'Smith, Jane': ");
		String anyName = keyboard.nextLine();
		
		/*
		 * "Seperates" the last name from the first name by taking the position of the last comma and 
		 * makes the last name the characters between 0 and the comma and makes the characters from the
		 * space after the comma to the end of the string
		 */
		int comma = anyName.lastIndexOf(","); 
		String last = anyName.substring(0, comma);
		String first = anyName.substring(comma + 1);
		fullName = first + " " + last;
	}
	
	/*
	 * Asks the user to input the program and exam grades for the student
	 */
	private void setGrades(){
		System.out.println("\nPlease, enter the program and exam grades for" + fullName + ":");
		grade.setup();

	}
	
	/*
	 * Displays the name of the student along with their grades.
	 */
	
	public void display(){
		System.out.println(fullName + ":     "); 
		grade.display();
		System.out.println(" " + grade.average());
		System.out.println();
	

	}
	
	// the overall grade of the student
	public double overallGrade(){
		return grade.average();
		
	}
}
