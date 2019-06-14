
import java.util.Arrays;

public class Matrix {
        
        
        public int [][] matrix;
        
        //Constructor
        public Matrix(int n){
                matrix = makeMatrix(n);
        }
        
        //Special Constructor: for inputting test case
        public Matrix(int [][] array){
                matrix = array;
        }
        
        //randomly generating a n x n matrix with range of -127 to 127 O(n^2)
        private int[][] makeMatrix(int n){
                int matrix[][] = new int[n][n];
                
                for(int i=0; i<n; i++){
                        for(int j=0; j<n; j++){
                                matrix[i][j] = -127 + (int)(Math.random() * ((127 + 127) + 1));
                        }
                }
                return matrix;
        }
        
        
        //Core kadane's Algorithm - O(n)
        private int[][] core(int array[], int n){
                int max = -127*n;
                int currMax = 0;
                int maxStart = 0;
                int maxEnd = 0;
                int start = 0;
                int [] tmp = new int[array.length];
                int count = 0;
                int [][]result = new int [2][];
                
                
                //O(n)
                for (int i=0; i < n; i++){
                        for (int j=i; j < array.length; j=j+n){
                                tmp[count] = array[j];
                                count++;
                        }
                }
                
                //O(n)
                for (int end = 0; end <= tmp.length - tmp.length/n; end = end + tmp.length/n){
                        for (int i = end; i < end + tmp.length/n; i++){
                                currMax = currMax + tmp[i];
                        }
                        
                        if (currMax > max){
                                max = currMax;
                                maxStart = start;
                                maxEnd = end + tmp.length/n;
                        }
                        
                        if (currMax < 0){
                                currMax = 0;
                                start = end + tmp.length/n;
                        }
                }
                
                int [] sum = new int [1];
                sum[0] = max;
                
                result[0] = Arrays.copyOfRange(tmp, maxStart, maxEnd);
                result[1] = sum;
                
                return result;
        }
        
        

        //Combining two array into one array O(n+m)
        private int[] combine(int array1[], int array2[]){
                int n = array1.length;
                int m = array2.length;
                
                int [] result = new int[n+m];
                
                for (int i = 0; i < n; i++){
                        result[i] = array1[i];
                }
                
                for (int j = 0; j < m; j++){
                        result[n+j] = array2[j];
                }
                return result;
        }
        

        //Apply Kadane's Algorithm on 2D matrix - O(n^2) + O(n^3) = O(n^4)
        public int[] kadane(){
                int n = matrix.length;
                int count = 0;
                int [] empty = new int[]{};
                int curMax = -128*n;
                
                int [][] result = new int[(n*(n+1))][];
                
                int[][] row= new int[n][n];
                int[][] column= new int[n][n];
                
                //scan all rows and columns - O(n^2)
                for (int r=0; r < n; r++){
                        for(int c=0; c < n; c++){
                                row[r][c] = matrix[r][c];
                                column[r][c] = matrix[c][r];
                        }
                }
                
                int [] tmp1 = empty;
                int [] tmp2 = empty;
                
                int [][] maxSum1 = new int [n*n][];
                int [][] maxSum2 = new int [n*n][];
                
                int [] maxArray = empty;
                
                //generate all combination of rows and columns - 2*(n*(n+1)/2) = O(n^3) * O(n) => O(n^4)
                //n
                for(int i=0; i < n; i++){
                        //n
                        for (int j = i; j < n; j++){
                                
                                //combining tow array - O(n): can improve to O(1) by using other container...(e.g: linked list)
                                tmp1 = combine(tmp1, row[j]);
                                tmp2 = combine(tmp2, column[j]);
                                
                                result[count] = tmp1;
                                result[count + n*(n+1)/2] = tmp2;
                                
                                count++;
                        }
                        
                        //Apply the Kadane's Algorithm to produce largest sum of a continuous sequence - O(n)
                        //for row
                        maxSum1 = core(tmp1, n);
                        //for column
                        maxSum2 = core(tmp2, n);
                        
                        //check if it is the maximum sum
                        
                        //for row's combination
                        if (maxSum1[1][0] > curMax){
                                curMax = maxSum1[1][0];
                                maxArray = maxSum1[0];
                        }
                        
                        //for column's combination
                        if (maxSum2[1][0] > curMax){
                                curMax = maxSum2[1][0];
                                maxArray =  maxSum2[0];
                        }
                        
                        //clear the tmp array
                        tmp1 = empty;
                        tmp2 = empty;
                }
                
                return maxArray;
        }
        
}
