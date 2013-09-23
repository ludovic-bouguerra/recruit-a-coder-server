package fr.ludovicbouguerra.ecodigo.result;

import java.io.Serializable;


public class Result implements IResult, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -186510338046406720L;
	private String inputData;
	private String outputData;
	private String expectedData;
	private boolean isValid;

	
	public Result(String inputData, String outputData) {
		super();
		this.inputData = inputData;
		this.outputData = outputData;

	}
	
	public Result(String inputData, String outputData, String expectedData,
			boolean isValid) {
		super();
		this.inputData = inputData;
		this.outputData = outputData;
		this.expectedData = expectedData;
		this.isValid = isValid;
	}
	
	
	@Override
	public String getInputData() {
		return inputData;
	}

	@Override
	public String getOutputData() {
		return outputData;
	}

	@Override
	public String getExpectedData() {
		return expectedData;
	}

	@Override
	public boolean isValid() {
		return isValid;
	}

}
