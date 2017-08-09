import java.util.Scanner;

/**
@author	Jeffrey Martinez

Purpose:	Defines a complete set of grades recieved by a student.

Input:		The grade of an individual student.

Output:		Prints the exam grade and program grade; calculates the average grade
			and returns it so it can be used later in the Example02 Class
*/

public class Grades {
	/*
	 * declares the variables
	 */
	private double programGrade, examGrade;
	
	private Scanner keyboard = new Scanner(System.in);
	
	/*
	 * Sets the complete set of grades
	 */
	public void setup(){
		programGrade = keyboard.nextInt();
		examGrade = keyboard.nextInt();
	}
	
	/*
	 * displays the complete set of grades
	 */
	public void display(){
		System.out.print(programGrade + " " + examGrade );
		
		
	}
	
	/*
	 * returns the average of the complete of grades
	 */
	public double average(){
		// computes the overall grade
		final double
			PROGRAM_WEIGHT = .40,
			EXAM_WEIGHT = 1 - PROGRAM_WEIGHT;
		
		double overallGrade;
		
		overallGrade = programGrade * PROGRAM_WEIGHT + examGrade * EXAM_WEIGHT;
		
		return overallGrade;
	}
	
}
