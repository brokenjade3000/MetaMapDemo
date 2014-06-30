package edu.utah.bmi.metamap;

import gov.nih.nlm.nls.metamap.Ev;
import gov.nih.nlm.nls.metamap.MetaMapApi;
import gov.nih.nlm.nls.metamap.MetaMapApiImpl;
import gov.nih.nlm.nls.metamap.PCM;
import gov.nih.nlm.nls.metamap.Result;
import gov.nih.nlm.nls.metamap.Utterance;

import java.util.List;

public class SimpleRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MetaMapApi api=new MetaMapApiImpl();
		String query=args[0];
		try{
			//let me try moving the mmap parse inside the try block to see if I can catch those mysterious mmap errors
			//OK, there was text, do something with it. First, stuff it through the 'map:
			System.out.println("$"+args+"$");
			List<Result> resultList = api.processCitationsFromString(query);
			
			//They do get caught, but it seems that nothing ever works again after that.
			
			for(Result result: resultList) {
				for (Utterance utterance: result.getUtteranceList()) {
					System.out.println("Utterance:");
					System.out.println(" Id: " + utterance.getId());
					System.out.println(" Utterance text: " + utterance.getString());
					System.out.println(" Position: " + utterance.getPosition());
					for(PCM pcm:utterance.getPCMList()) {
						System.out.println("Phrase:");
						System.out.println(" text: " + pcm.getPhrase().getPhraseText());						
						System.out.println("Candidates:");
				          for (Ev ev: pcm.getCandidateList()) {
				            System.out.println(" Candidate:");
				            System.out.println("  Score: " + ev.getScore());
				            System.out.println("  Concept Id: " + ev.getConceptId());
				            System.out.println("  Concept Name: " + ev.getConceptName());
				            System.out.println("  Preferred Name: " + ev.getPreferredName());
				            System.out.println("  Matched Words: " + ev.getMatchedWords());
				            System.out.println("  Semantic Types: " + ev.getSemanticTypes());
				            System.out.println("  Sources: " + ev.getSources());
				            System.out.println("  Span position infor: " + ev.getPositionalInfo());
				          }
					}// end PCM
				}//end Utterance
			} //end Result

		} catch (Exception e) {
			e.printStackTrace();
			
		}			
		
		
	}
	
	

}
