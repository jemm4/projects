/**
	@author	Jeffrey Martinez
	
	Purpose:	Calculates the overall average of the students(3) and prints it. 

	Input:		The individual grades of the students.

	Output:		The overall average grade between the two students is then put toward the Student Class. 
*/

public class Example02
{
	public static void main(String[] args)
	{
		double theSectionAverage;					//	To determine the section average
		
		System.out.println("Bonjour!");
		
		/*
		 * instantiates three seperate students with the Student Class
		 */
		Student First = new Student();
		Student Second = new Student();
		Student Third = new Student();
		
		/*
		 * sets all the attributes of the student on three different occasions(representing three students)
		 */
		First.setup();
		Second.setup();
		Third.setup();
		
		/*
		 * Displays "full name:" along with the program grade and the exam grade
		 */
		First.display();
		Second.display();
		Third.display();
	
		
		theSectionAverage = ((First.overallGrade()	//	Computes the section average
							+ Second.overallGrade() + Third.overallGrade())/3);
		
		/*
		 * Prints the calculated average of the three students
		 */
		System.out.println("\nThe overall section average is: " + theSectionAverage);
		System.out.println();
		System.out.println("Au revoir!");
	}
}
