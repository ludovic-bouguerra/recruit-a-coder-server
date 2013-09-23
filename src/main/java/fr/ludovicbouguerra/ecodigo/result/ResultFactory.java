package fr.ludovicbouguerra.ecodigo.result;

public class ResultFactory implements IResultFactory {

	private static IResultFactory instance;
	
	private ResultFactory(){
		
	}
	
	
	@Override
	public IResult createResult(String inputData, String outputData) {
		return new Result(inputData, outputData);
	}
	
	
	@Override
	public IResult createResult(String inputData, String outputData,
			String expectedData, boolean isValid) {
		return new Result(inputData, outputData, expectedData, isValid);
	}
	
	public static IResultFactory getInstance(){
		if (instance == null){
			instance = new ResultFactory();
		}
		return instance;
	}

}
