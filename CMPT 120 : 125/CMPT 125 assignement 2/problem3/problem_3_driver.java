/**
 * @(#)problem_3_driver.java
 *
 *
 * @author 
 * @version 1.00 2013/4/10
 */
import java.util.ArrayList;
public class problem_3_driver {
        
    
    public static void main(String[] args) {
        Haar_Wavelet_Transformation s = new Haar_Wavelet_Transformation();
        ArrayList<Integer> sequence = s. GenerateSequence();
        int level = s.levels_of_transformation(sequence);
        ArrayList<Integer> transformed_sequence = s.HarrTransformation(sequence, level);
        
    }
}
