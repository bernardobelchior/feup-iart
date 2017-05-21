package datamanipu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Vector <String> values = new Vector<String>();
		 String csvFile = "dados.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";
	        int i = 0;
	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {
	            	
	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);
	                
	                values.add((country[30]));
	                i++;
	            }
	            
	            

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        
	        
	        BufferedWriter bw = null;
			FileWriter fw = null;

			try {


				fw = new FileWriter("file.txt");
				bw = new BufferedWriter(fw);
				
				for (int j = 0; j < values.size(); j++)
				{
					bw.write(values.get(j) + '\n');
				}

				System.out.println("Done");

			} catch (IOException e) {

				e.printStackTrace();

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}

	        
	        
	        
	        
	        

	}

}
