 package task2;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * Knn algorithm implementation
 */
public class Implementation {

	// created lists for storing training and testing datasets label and features.

	private List<double[]> trainfeatures = new ArrayList<>();
	private List<String> trainlabel = new ArrayList<>();

	private List<double[]> testfeatures = new ArrayList<>();
	private List<String> testlabel = new ArrayList<>();

	Scanner sc = new Scanner(System.in);
	int knn_value = 1;
	int totalNumberOfLabel = 0;

	
	void loadtrainData(String filename) throws NumberFormatException, IOException {

		File file = new File(filename);

		try {
			BufferedReader readFile = new BufferedReader(new FileReader(file));
			String line;
			while ((line = readFile.readLine()) != null) {

				String[] split = line.split(",");
				double[] feature = new double[split.length - 1];
				for (int i = 0; i < split.length - 1; i++)
					feature[i] = Double.parseDouble(split[i]);
				trainfeatures.add(feature);
				trainlabel.add(split[feature.length]);
				
			}
			readFile.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}

	}

	
	void loadtestData(String testfilename) throws NumberFormatException, IOException {

		File testfile = new File(testfilename);

		try {
			BufferedReader testreadFile = new BufferedReader(new FileReader(testfile));
			PrintWriter pw = new PrintWriter("RealLabel.txt");
			String testline;
			
			while ((testline = testreadFile.readLine()) != null) {

				String[] split = testline.split(",");
				double[] feature = new double[split.length - 1];
				for (int i = 0; i < split.length - 1; i++)
					feature[i] = Double.parseDouble(split[i]);
				testfeatures.add(feature);
				testlabel.add(split[feature.length]);
				// writing original label for test data to file and counting number of label.
				pw.println(split[feature.length]);
				totalNumberOfLabel++;

			}
			pw.close();
			testreadFile.close();
		}

		catch (FileNotFoundException e) {
			// TODO Auto catch block
			e.printStackTrace();
		}
		
			
	}
	
	

	/*
	 * Based on user input, calling algorithm to calculate distance
	 */
	

	/*
	 * Distance1 Calling  method distancesrch to calculate distance and writing
	 * output to file.
	 * 
	 */

	
	@SuppressWarnings("unchecked")
	void Distance1() throws IOException {
		KNN_Distance distancesrch = new KNN_Distance();

		Iterator<double[]> testITR = testfeatures.iterator();

		PrintWriter pw = new PrintWriter("Result.txt");

		while (testITR.hasNext()) {
			double testF[] = testITR.next();
			Iterator<double[]> trainITR = trainfeatures.iterator();
			int OBJcount = 0;
			ArrayList<DistanceAndFeatures> ts = new ArrayList<>();
			while (trainITR.hasNext()) {
				double trainF[] = trainITR.next();
				double dist = 0;
				dist = distancesrch.getDistance1(trainF, testF);

				String trainFeat = trainlabel.get(OBJcount);
				DistanceAndFeatures DfObject = new DistanceAndFeatures(dist, trainFeat);
				ts.add(DfObject);				
				OBJcount++;

			}
			Collections.sort(ts);
 
			/*
			 * counting top predicted label based on k value
			 */
			int flag = 0, IR_Setsosa = 0, IR_Versicolor = 0, IR_Virginica = 0;

			while (flag < knn_value) {
				DistanceAndFeatures s = ts.get(flag);
				String s1 = s.getLabel();
				if (s1.equals("Iris-setosa"))
					IR_Setsosa++;
				else if (s1.equals("Iris-versicolor"))
					IR_Versicolor++;
				else if (s1.equals("Iris-virginica"))
					IR_Virginica++;
				flag++;

			}
			
			if (IR_Setsosa > IR_Versicolor && IR_Setsosa > IR_Virginica) {
				System.out.println("Iris_Sestosa=" + IR_Setsosa);
				pw.println("Iris-setosa");

			} else if (IR_Versicolor > IR_Setsosa && IR_Versicolor > IR_Virginica) {
				System.out.println("Iris_Versicolor=" + IR_Versicolor);
				pw.println("Iris-versicolor");
			}

			else if (IR_Virginica > IR_Setsosa && IR_Virginica > IR_Versicolor) {
				System.out.println("Iris_Virginica=" + IR_Virginica);
				pw.println("Iris-virginica");
			}
		}
		pw.close();
		distancesrch.accuracy(totalNumberOfLabel);
		
	}

	
	void getKValue() {

		System.out.println("Enter the K value of KNN ");
		knn_value = sc.nextInt();
		// Restricted k value less 50.
		
	}

	void setTest(double [] myfeature, String myLabel) throws IOException {
		BufferedWriter pw = new BufferedWriter(new FileWriter("TestLabel.txt"));
		testfeatures.add(myfeature);
		testlabel.add(myLabel);
		pw.write(myLabel);
		pw.close();
		totalNumberOfLabel++;
		
	}
	
	
	
	public void writefile() throws IOException {	
		
		//Collections.sort(scores);
		
		int counter = 0;
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"))){
		for(double[] p : testfeatures) {
			for(int i = 0; i <= 4; i++ ) {			
			if(i == 4) {
				writer.write(testlabel.get(counter)+"\n");
				counter++;
			}
			else {
				writer.write(p[i]+",");
			}
			}
		}
		}
						
	}
	
	void cleanStard() {
		trainfeatures.clear();
		trainlabel.clear();
		testlabel.clear();
		testfeatures.clear();		
		totalNumberOfLabel = 0;
		
				
	}

}
