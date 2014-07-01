package edu.utah.bmi.metamap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Interpret the metamap's abbreviated semantic types to UMLS semantic type code and full name. 
 * 
 * @author Jianlin Shi
 *
 */
public class STYInterpretor {
	private TreeMap<String, String> meaningMap = new TreeMap<String, String>();

	public STYInterpretor(String styFile) {
		BufferedReader fr;
		try {
			File file = new File(styFile);
			fr = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = fr.readLine()) != null) {
				String key = line.substring(0, line.indexOf("|"));
				meaningMap.put(key, line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMeaning(String metamapSTY) {
		return meaningMap.get(metamapSTY);

	}
	public List<String> getMeanings(List<String> metamapSTYs){
		List<String> output=new ArrayList<String>();
		for(String ele:metamapSTYs){
			output.add(meaningMap.get(ele));
		}						
		return output;
		
	}

}
