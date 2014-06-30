package edu.utah.bmi.metamap;

import gov.nih.nlm.nls.metamap.Ev;
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
public class SimpleGetCandidates {

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
				          for (Ev ev: pcm.getCandidateList()) {
				            System.out.println(" Candidate:");
				            System.out.println("  Score: " + ev.getScore());
				            System.out.println("  Concept Id: " + ev.getConceptId());
				            System.out.println("  Concept Name: " + ev.getConceptName());
				            System.out.println("  Preferred Name: " + ev.getPreferredName());
				            System.out.println("  Matched Words: " + ev.getMatchedWords());
				            System.out.println("  Semantic Types: " + ev.getSemanticTypes());
//				            System.out.println("  Semantic Types: " + sytInterpretor.getMeanings(ev.getSemanticTypes()));
				            System.out.println("  Sources: " + ev.getSources());
//				            this is the span information 
				            List<Position> positions=ev.getPositionalInfo();
				            System.out.println("  Span position infor: " + positions);				            
//				            how to get the begin and end of span
				            for(Position p:positions){
				            	System.out.println("\tspan begin:\t"+p.getX());
				            	System.out.println("\tspan end:\t"+(p.getX()+p.getY()));
				            }
				          }
					}
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			
		}			
		
		
	}
	
	

}
