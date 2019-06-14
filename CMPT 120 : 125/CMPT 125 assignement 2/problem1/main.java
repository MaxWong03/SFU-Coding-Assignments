import java.util.Arrays;

public class main {

	public static int FindMaxSum(int array[]){
		int sum = 0;
		for (int i=0; i < array.length; i++){
			sum = sum + array[i];
		}
		return sum;
	}
	
	public static void main(String arg[]){
		
	    //test
	    //int[][] a = {{1, 1, 1, 1} , {1, 1, 1, -1} , {1, 1, 1, -1} , {1, 1, 1, -1}};
		
		//int[][] b = {{0, -2, -7, 0} , {9, 2, -6, 2} , {-4, 1, -4, 1} , {-1, 8, 0, -2}};
		
		//int[][] c = {{126,-73,-116} , {120,-67,-83} , {-113,-92,-5}};  
		
		//int[][] x = {{2,-2,-4,4,-5}, {1,-3,3,-4,-3}, {-4,2,4,-3,5}, {3,0,0,0,-5}, {-2,2,5,2,4}}; 
		
	
		Matrix test = new Matrix(100);
		
      	long startTime = System.currentTimeMillis(); //gets current time in milliseconds 
      	startTime = System.currentTimeMillis();
		int [] f = test.kadane();		
		System.out.println("Maximum Sum: "+FindMaxSum(f)); 	
		long endTime = System.currentTimeMillis(); //gets current time in milliseconds 
		System.out.println("Time taken: "+(endTime-startTime)+" milliseconds.");
		



		
	}
}