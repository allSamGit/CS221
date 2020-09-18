import java.io.FileNotFoundException;
import java.io.PrintStream;

public class order2 {
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream outfile = new PrintStream("doSome.csv");
		outfile.println("size, statements");
	
		for (int size = 0; size < 100; size += 1) {
			int[] array = MethodsToAnalyze.randomizedArray(size);
			MethodsToAnalyze.order2(array);
			Long statementNum= MethodsToAnalyze.getStatements();
			outfile.println(size + ", " + statementNum);
			System.out.printf("\nfor n = %d, took %d statements\n",size, statementNum);
			System.out.printf(" ");
		}
		outfile.close();
		

	}
}
