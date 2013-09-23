package fr.ludovicbouguerra.ecodigo.result;

public interface IResultFactory {
	
	
	IResult createResult(String inputData, String outputData, String expectedData,
			boolean isValid);

	IResult createResult(String inputData, String outputData);
	
	
}
