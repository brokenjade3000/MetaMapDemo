package edu.utah.bmi.metamap;

import gov.nih.nlm.nls.metamap.Ev;
import gov.nih.nlm.nls.metamap.Mapping;
import gov.nih.nlm.nls.metamap.MetaMapApi;
import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.PCM;
import gov.nih.nlm.nls.metamap.Result;
import gov.nih.nlm.nls.metamap.Utterance;

import java.util.List;
import java.util.TreeMap;

import gov.nih.nlm.nls.metamap.Position;

/**
 * 
 * This demo code is adopted from MetaMap official API documentation
 * 
 * http://metamap.nlm.nih.gov/Docs/README_javaapi.html
 * 
 * @author Jianlin Shi
 *
 */
public class SimpleGetMapping {

	public static void main(String[] args) {
		
		
		String query="We did obtain a chest CT.";
//		initiate a semantic type interpreter
//		STYInterpretor sytInterpretor=new STYInterpretor("data/SemanticTypes_2013AA.txt");
		
//		 Instantiating the API
		MetaMapApi api=new MetaMapApiImpl();		
		try{
//			Performing a query 	
			List<Result> resultList = api.processCitationsFromString(query);				
			for(Result result: resultList) {
//				produces a list of the utterances: 
				for (Utterance utterance: result.getUtteranceList()) {
					System.out.println("Utterance:");
					System.out.println(" Id: " + utterance.getId());
					System.out.println(" Utterance text: " + utterance.getString());
					System.out.println(" Position: " + utterance.getPosition());
//					To get the list of phrases, candidates, and mappings associated with an utterance 
					for(PCM pcm:utterance.getPCMList()) {						
						System.out.println("Phrase:");
						System.out.println(" text: " + pcm.getPhrase().getPhraseText());						
						System.out.println("Candidates:");
//						get the candidate list 			          

						System.out.println("Mappings:");
				          for (Mapping map: pcm.getMappingList()) {
				            System.out.println(" Map Score: " + map.getScore());
				            for (Ev mapEv: map.getEvList()) {
				              System.out.println("   Score: " + mapEv.getScore());
				              System.out.println("   Concept Id: " + mapEv.getConceptId());
				              System.out.println("   Concept Name: " + mapEv.getConceptName());
				              System.out.println("   Preferred Name: " + mapEv.getPreferredName());
				              System.out.println("   Matched Words: " + mapEv.getMatchedWords());
				              System.out.println("   Semantic Types: " + mapEv.getSemanticTypes());
//					          System.out.println("  Semantic Types: " + sytInterpretor.getMeanings(mapEv.getSemanticTypes()));
				              System.out.println("   MatchMap: " + mapEv.getMatchMap());
				              System.out.println("   MatchMap alt. repr.: " + mapEv.getMatchMapList());
				              System.out.println("   is Head?: " + mapEv.isHead());
				              System.out.println("   is Overmatch?: " + mapEv.isOvermatch());
				              System.out.println("   Sources: " + mapEv.getSources());
				              System.out.println("   Positional Info: " + mapEv.getPositionalInfo());
				            }
				          }						
						
						
//				           
					}
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
		}			
		
		
	}
	
	

}
