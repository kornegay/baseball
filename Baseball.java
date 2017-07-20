package bb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This program reads raw baseball batting data and calculates commonly used baseball statistics with it.  It uses Sean Lahman's
 * historical baseball batting statistics from seanlahman.com.  Program can be used for statistics from 1955 to the present.  1955
 * was the first year when all modern batting statistics were recorded.  The program is designed for calculating a single season's
 * batting statistics.
 * @author Kornegay
 *
 */
public class Baseball {

	public static void main(String[] args) {
		String file = "2016.csv";
		
		HashMap<String, BattingStats> hm = new HashMap<String, BattingStats>();
		
		dataReader(hm, file);
		
		System.out.println(hm.toString());
		System.out.println(BattingStats.count);
	}
	
	/**
	 * Attempts to load a csv file and create BattingStats objects with the data
	 * @param hm - hashmap storing player names and their stats
	 * @param fileLoc - location of file to be read
	 * @throws FileNotFoundException
	 */
	static void dataReader(HashMap<String, BattingStats> hm, String fileLoc) {
		String line;
		
		try {
			FileReader fr = new FileReader(fileLoc);
			BufferedReader br = new BufferedReader(fr);
			
			br.readLine();
			
			while((line = br.readLine()) != null){
				new BattingStats(line, hm);
			}
			
			fr.close();
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileLoc + "'"); 
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
	}

}
