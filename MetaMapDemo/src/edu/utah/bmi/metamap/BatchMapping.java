package edu.utah.bmi.metamap;

import gov.nih.nlm.nls.metamap.Ev;
import gov.nih.nlm.nls.metamap.MetaMapApi;
import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.PCM;
import gov.nih.nlm.nls.metamap.Result;
import gov.nih.nlm.nls.metamap.Utterance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * 
 * This is a demo of using MetaMap to map a list of concepts (in a csv format)
 * 
 * @author Jianlin Shi
 *
 */
public class BatchMapping {

	private static MetaMapApi api;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader fr;
		BufferedWriter bw;
		String datafile = "data/semeval_devel_uniconcepts.csv";
		String outputfileName = "data/semeval_devel_uniconcepts_mapped.csv";
//		record starting time before initiation
		long startTime1 = System.nanoTime();
		
		File outputfile = new File(outputfileName);
		api = new MetaMapApiImpl();
//		record starting time after initiation
		long startTime2 = System.nanoTime();
		try {
			// initiate file reader
			File file = new File(datafile);
			fr = new BufferedReader(new FileReader(file));
			String line = "";
			// initiate file writer
			if (!outputfile.exists()) {
				outputfile.createNewFile();
			}
			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			// start mapping
			while ((line = fr.readLine()) != null) {
				if (line.indexOf(",") != -1) {
					String[] row = line.split(",");
					bw.write(line + "," + getMetaMapCUI(row[0])+"\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		System.out.println("Initiating Time: "+ (startTime2 - startTime1) / 1000000 + "ms");
		System.out.println("Processing Time: "+ (endTime - startTime2) / 1000000 + "ms");
		System.out.println("Total Time: "+ (endTime - startTime1) / 1000000 + "ms");

	}

	/**
	 * get the mapped cui, score and preferred name (only return the top one)
	 * @param concept
	 * @return
	 */
	public static String getMetaMapCUI(String concept) {
		try {
			List<Result> resultList = api.processCitationsFromString(concept);
			for (Result result : resultList) {
				for (Utterance utterance : result.getUtteranceList()) {
					for (PCM pcm : utterance.getPCMList()) {
						List<Ev> candlist = pcm.getCandidateList();
						if (candlist.size() > 0)
							return candlist.get(0).getConceptId().substring(1)+","+candlist.get(0).getScore()+","+candlist.get(0).getPreferredName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "UI-less,0,";
	}

}
