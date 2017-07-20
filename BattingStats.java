package bb;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Class to calculate from raw data and store baseball statistics.
 * @author Kornegay
 *
 */
public class BattingStats {
	static int count; //total player count
	static String[] raw = new String[22]; //used to store player information and raw hitting data
	
	String name; //player name
	int tb, pa; //total bases, plate appearances
	double avg, obp, slg, ops; //batting average, on base percentage, slugging percentage, on base plus slugging
	
	/**
	 * Default constructor
	 */
	public BattingStats(){
		name = null;
		tb = -1;
		pa = -1;
		avg = -1;
		obp = -1;
		slg = -1;
		ops = -1;
	}
	
	/**
	 * Constructs BaggingStats object with all stats calculated, adds object to hashmap
	 * @param batStr - raw batting data
	 * @param hm - hashmap to store names and averages
	 */
	public BattingStats (String batStr, HashMap<String, BattingStats> hm){
		StringTokenizer st = new StringTokenizer(batStr);
		
		for (int i = 0; i < 22; i++){
			raw[i] = st.nextToken(",");
		}
		
		if (Integer.parseInt(raw[2]) == 1){
			count++;
		}
		
		name = raw[0];
		
		setPATB();
		setAverage();
		setOBP();
		setSlg();
		setOPS();
		
		hm.put(name, this);
	}
	
	/**
	 * Calculates and sets plate appearances and total bases statistics
	 */
	void setPATB(){
		//plate appearances is the sum of at bats, walks, hits by pitches, sac hits, and sac flies respectively
		pa = Integer.parseInt(raw[6]) + Integer.parseInt(raw[15]) + Integer.parseInt(raw[18]) + 
				Integer.parseInt(raw[19]) + Integer.parseInt(raw[20]);
		
		//total hits with an extra base added four doubles, two extra for triples, and three extra for home runs
		tb = Integer.parseInt(raw[8]) + Integer.parseInt(raw[9]) + 2 * Integer.parseInt(raw[10]) + 3 * Integer.parseInt(raw[11]);
	}
	
	/**
	 * Calculates batting average
	 */
	void setAverage(){
		int b = Integer.parseInt(raw[6]); //at bats
		int h = Integer.parseInt(raw[8]); //hits
		
		avg = (double)h / (double)b;
	}
	
	/**
	 * Calculates on base percentage
	 */
	void setOBP(){
		//sum of at bats, walks, and hits by pitches
		int reachedBase = Integer.parseInt(raw[8]) + Integer.parseInt(raw[15]) + Integer.parseInt(raw[18]);
		
		//obp calculations don't factor in sacrifice hits so they're subtracted out from plate appearances
		obp = (double)reachedBase / (double)(pa - Integer.parseInt(raw[19]));
	}
	
	/**
	 * Calculates slugging percentage
	 */
	void setSlg(){
		//slg is total bases divided by at bats
		slg = (double)tb / (double)Integer.parseInt(raw[6]);
	}
	
	/**
	 * Calculates on base plus slugging
	 */
	void setOPS(){
		//ops is on base plus slugging
		ops = obp + slg;
	}
	
	/**
	 * String representation of data
	 */
	public String toString(){
		DecimalFormat numberFormat = new DecimalFormat("#.000");
		
		return name + "\nAVG - " + numberFormat.format(avg) + "\nOBP - " + numberFormat.format(obp) + "\nSLG - "
			+ numberFormat.format(slg) + "\nOPS " + numberFormat.format(ops);
	}
}
