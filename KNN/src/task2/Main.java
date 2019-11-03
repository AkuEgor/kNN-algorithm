package task2;

import java.io.IOException;
import java.util.Scanner;



public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Implementation implement = new Implementation();
		
		while(true) {
			
		addData(implement);
		
		
		}

	}

	static void calculate(Implementation trn_ds) throws NumberFormatException, IOException {		
					
		System.out.println("Enter training dataset file name");
		String trainfilename=sc.nextLine();
		System.out.println("Enter test dataset file name");
		String testfilename=sc.nextLine();
		trn_ds.getKValue();
		  
		trn_ds.loadtrainData(trainfilename);
		trn_ds.loadtestData(testfilename);
		trn_ds.Distance1();
		//sc.close();
	}
	
	static void addData(Implementation implement) throws IOException {
		

		Main: while (true) {
			
			System.out.println("Do you want to input new data?"
					+ " Enter: 'Yes' or 'No'");
			String answer = sc.nextLine();			
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
				
				System.out.println("Enter new features:");
				String line = sc.nextLine();
				String[] split = line.split(",");
				double[] feature = new double[split.length];
				for (int i = 0; i < split.length; i++) {
					feature[i] = Double.parseDouble(split[i]);
				}
				System.out.println("Enter new label:");
				String label = sc.nextLine();
				
					implement.setTest(feature, label);
					
					implement.getKValue();
					implement.loadtrainData("train.txt");
					implement.Distance1();
					implement.cleanStard();
					
					break Main;
				} 								

			 else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
				implement.cleanStard();
				calculate(implement);
			} else {
				System.out.println("Unknown answer!");

				break Main;
			}
		}
		
	}

}
