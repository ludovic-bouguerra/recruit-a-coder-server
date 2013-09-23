package fr.ludovicbouguerra.ecodigo.resultcomparator;

import java.util.ArrayList;
import java.util.Collection;

import fr.ludovicbouguerra.ecodigo.result.IResult;
import fr.ludovicbouguerra.ecodigo.result.ResultFactory;

public class SourceComparator implements ISourceComparator {

	@Override
	public ArrayList<IResult> compare(IComparator comprator,
			Collection<String> inputData, Collection<String> result,
			Collection<String> expectedResult) {

		ArrayList<IResult> results = new ArrayList<IResult>();

		for (int i = 0; i < inputData.size(); i++) {

			boolean valid = comprator.compare(
					(String) expectedResult.toArray()[i],
					(String) result.toArray()[i]);
			results.add(ResultFactory.getInstance().createResult(
					(String) inputData.toArray()[i],
					(String) result.toArray()[i],
					(String) expectedResult.toArray()[i], valid));

		}

		return results;

	}

}
