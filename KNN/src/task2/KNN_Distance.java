package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/*
 * This class is for calculating distance. 
 *   */

public class KNN_Distance {
	
	
	
	public double getDistance1( double[] features1,  double[] features2) {
        double sum = 0;
        for (int i = 0; i < features1.length; i++)
        {  
            sum += Math.pow(features1[i] - features2[i], 2);                                  
        }        
    
        return Math.sqrt(sum);
    }
	
	
	public void accuracy(int totalNumberOfLabel) throws IOException {
		int count = 0;
		File file = new File("Result.txt");

		BufferedReader rf = new BufferedReader(new FileReader(file));
		BufferedReader label = new BufferedReader(new FileReader(new File("RealLabel.txt")));
		String s = rf.readLine();
		while (s != null) {
			String lab = label.readLine();
			if (s.equals(lab)) {

			} else {
				count++;
				
			}

			s = rf.readLine();
		}

		System.out.println("Accuracy is: " + ((float) 100 - (((float) count / totalNumberOfLabel) * 100)) + "%");
		rf.close();
		label.close();

	}

}
