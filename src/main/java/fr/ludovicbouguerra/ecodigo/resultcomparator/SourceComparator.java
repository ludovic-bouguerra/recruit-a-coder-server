package fr.ludovicbouguerra.ecodigo.resultcomparator;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;


public class SourceComparator implements ISourceComparator {

	@Override
	public String compare(IComparator comprator,
			String inputData, String result,
			String expectedResult) throws UnexpectedResult {

			if (comprator.compare(expectedResult,result)){
				return result;
			}else{
				throw new UnexpectedResult(result + "expected > " +expectedResult);
			}

	}

}
