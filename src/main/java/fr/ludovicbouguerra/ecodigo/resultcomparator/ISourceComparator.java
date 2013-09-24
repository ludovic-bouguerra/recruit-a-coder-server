package fr.ludovicbouguerra.ecodigo.resultcomparator;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;



public interface ISourceComparator {
	
	
	public String compare(IComparator comprator,
			String inputData, String result,
			String expectedResult) throws UnexpectedResult;
	
}
