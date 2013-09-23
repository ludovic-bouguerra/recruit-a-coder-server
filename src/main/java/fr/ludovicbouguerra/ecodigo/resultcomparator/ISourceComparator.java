package fr.ludovicbouguerra.ecodigo.resultcomparator;

import java.util.Collection;

import fr.ludovicbouguerra.ecodigo.result.IResult;

public interface ISourceComparator {
	
	
	public Collection<IResult> compare(IComparator comprator, Collection<String> inputData, Collection<String> result,
			Collection<String> expectedResult);
	
}
