import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author SamanRastgar
 *
 */
/**
 * @author rastgarsaman
 *
 */
public class GridMonitor implements GridMonitorInterface
{
	private double[][] b,sumGrid,aveGrid,deltaGrid;//defining my values
	private boolean[][] dangerGrid;


	//string filename = Whatever Your Input Was;

	public GridMonitor(String fileName) throws FileNotFoundException{

		Scanner fileScan=null;

		File file = new File(fileName);
		fileScan = new Scanner(file);


		int height, width;

		height = fileScan.nextInt();
		width = fileScan.nextInt();

		//Creating an array size of first number(height) and second number(width)

		b = new double[height][width];

		while (fileScan.hasNextDouble()) {

			for(int i=0;i<b.length;i++)
			{
				for (int j=0;j<b[i].length;j++)
				{

					b[i][j] = fileScan.nextDouble();
				}
			}
		}

		//  Creating our needed grids

		sumGrid=new double[height][width];
		aveGrid=new double[height][width];
		deltaGrid=new double[height][width];
		dangerGrid=new boolean[height][width];

		for(int i=0;i<sumGrid.length;i++)
		{
			for (int j=0;j<sumGrid[i].length;j++)
			{
				double sumVal=0,aveVal=0,deltaVal=0;
				boolean flag;

				// checking i-1, j

				if(i-1>=sumGrid.length || i-1<0 || j>=sumGrid[i].length || j<0)
				{
					sumVal+=b[i][j];
				}
				else {
					sumVal+=b[i-1][j];
				}

				//checking i+1,j

				if(i+1>=sumGrid.length || i+1<0 || j>=sumGrid[i].length || j<0)
				{
					sumVal+=b[i][j];
				}
				else {
					sumVal+=b[i+1][j];
				}

				//checking i,j-1 

				if(i>=sumGrid.length || i<0 || j-1>=sumGrid[i].length || j-1<0)
				{
					sumVal+=b[i][j];
				}
				else {
					sumVal+=b[i][j-1];
				}

				//checking i,j+1

				if(i>=sumGrid.length || i<0 || j+1>=sumGrid[i].length || j+1<0)
				{
					sumVal+=b[i][j];
				}
				else {
					sumVal+=b[i][j+1];
				}

				sumGrid[i][j]=sumVal;

				//calculating average value for each element	

				aveVal=sumVal/4;

				aveGrid[i][j]=aveVal;

				deltaVal=aveVal/2;

				if(deltaVal<0) //changes negative values to positive values
				{
					deltaVal*=-1;
				}

				deltaGrid[i][j]=deltaVal;
				
				//comparing original value if it is in Danger range or not?
				
				if(b[i][j]<=(aveVal - deltaVal) || b[i][j]>= (aveVal + deltaVal)) {
					flag=true;
				}
				else { 
					flag=false;
				}

				dangerGrid[i][j]=flag;
			}

		}

		fileScan.close();
	}


	/** This function Returns Encapsulated Original Grid
	 ** @return baseGrid
	 **/
	@Override
	public double[][] getBaseGrid() {


		double[][] copyB=new double[b.length][b[0].length];
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				copyB[i][j]=b[i][j];
			}
		}
		return copyB;
	}
	/** This function returns Encapsulated Grid of surrounding summed up values 
	 ** @return sumGrid
	 */
	@Override
	public double[][] getSurroundingSumGrid() {

		double[][] copySurrSum=new double[b.length][b[0].length];
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				copySurrSum[i][j]=sumGrid[i][j];
			}
		}
		return copySurrSum;

	}

	/** This function returns Encapsulated Value of each Surrounding sumValues divided by 4
	 * @return surrondingAverage
	 */
	@Override
	public double[][] getSurroundingAvgGrid() {

		double[][] copyAve=new double[b.length][b[0].length];
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				copyAve[i][j]=aveGrid[i][j];
			}
		}
		return copyAve;
	}
	/** This function returns Encapsulated Value of each Average Grid divided by two
	 * @return deltaGrid
	 */
	@Override
	public double[][] getDeltaGrid() {

		double[][] copyDel=new double[b.length][b[0].length];
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				copyDel[i][j]=deltaGrid[i][j];
			}
		}
		return copyDel;
	}

	/** This function returns the value of DangerGrid which is the dangerous Elements in spaceship
	 * @return dangerGrid
	 */
	@Override

	public boolean[][] getDangerGrid() {

		boolean[][] copyDa=new boolean[b.length][b[0].length];
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b[i].length;j++)
			{
				copyDa[i][j]=dangerGrid[i][j];
			}
		}
		return copyDa;
	}

	/**This function Prints the Original grid Values
	 * 
	 * @return Grid
	 **
	 */
	public String toString()
	{
		String print="";
	
		for(int i=0;i<b.length;i++)
		{
			for(int j=0;j<b.length;j++)
			{		
				print+=b[i][j]+" ";
			}
			print+="\n";
		}
		return print;
	}
}



