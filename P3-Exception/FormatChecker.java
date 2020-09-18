/** @author Saman Rastgar
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FormatChecker {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		if(args.length==0) //checks if we are using command line prompt argument
		{
			System.err.println("Usage: $ java FormatChecker file1 [file2 ... fileN]"); //It will show the user how to enter the argument in command line.
		}
		else {
			ReadingFile(args);  //We read our static defined method
			
		}

}
/**@param String[] args (command line Argument)
 * 
 *I have defined this method to make my program more readable and use my function in try catch block 
 * in a more dynamic way.
 *  
 *
*/
@SuppressWarnings("resource")
private static void ReadingFile(String[] args) //we don't need to throw exceptions cause try catch block inside of it will handle this task by itself
{
	for(int i=0;i<args.length;i++)
	{
		try {
			File fileName=new File(args[i]); //we pass the arguments into the file using command line argument 
			
		    Scanner scan=null;
			scan = new Scanner(fileName); //set the Scanner to the file name
			System.out.println(fileName.getName());//This method Prints the file name
			
		  String firstInput = scan.next();
		  int row= Integer.parseInt(firstInput);
		  String secondInput = scan.next();
		  int col= Integer.parseInt(secondInput);
		  int count=0;
			

		  if(row<=0) //checks if row is a positive number
		  {
			  throw new Exception("row should be greater than zero"); 
		  }
		  if(col<=0) //checks if column is a positive number
		  {
			  throw new Exception("column should be greater than zero");
		  }
		  
		  int rowCount = 0,colCount,colCounter=0;  //we instansitate our variables 
		  String restOfLine=scan.nextLine();
		  Scanner lengthScan=new Scanner(restOfLine);
		 if( lengthScan.hasNext())
		 {
			 throw new Exception("Size dimension is not correct");
		 }
			while (scan.hasNextLine()) {
				
				colCount=0;
				String line = scan.nextLine();  //we check each line of file 
				if(line.length()!=0)
				{
				Scanner lineScan = new Scanner(line); 
				while (lineScan.hasNextLine()) {  //iterate through the file with while loop
					String token = lineScan.next();
				
					
					Double tokenDouble=Double.parseDouble(token); //parse double in case we need to use
					
					
					colCount++;
					colCounter=colCount;
				}
			
				rowCount++;
				lineScan.close();  //Scanner Closed
				}
		  }
			 if(rowCount!=row)
			  {
				  throw new Exception("First input doesn't match the row");  //checks if the first two number that demonstrates row and column in the file 
				                                                             //matches excatly the same size of our existed array
			  }
			  if(colCounter!=col)
			  {
				  throw new Exception("second input doesn't match the column");
			  }
			  
			  System.out.println("VALID"); //If we didn't Stuck between any of these exception our file is valid cause there was no error associated with it.
			
		  scan.close();
			}
	
		catch(FileNotFoundException e) 
		{
			System.err.println("File Not Found "+e.toString());//Prints the File Not Found error declaring (no such a file or directory)
			System.out.println("INVALID");
			System.out.println();
		}
		catch(InputMismatchException e) {
			
			System.err.println(e.toString());  // print if we have any mismatch type argument
			System.out.println("INVALID");
			System.out.println();
			
		}
		catch(NumberFormatException e)
		{
			System.err.println(e.toString()); // prints error if we have have an element with different notation format
			System.out.println("INVALID");
			System.out.println();
		}
		catch(Exception e)
		{
			System.err.println(e.toString());  //It catches the error messages that we specify for every block of code
			System.out.println("INVALID");
			System.out.println();
		}
	}
}
}