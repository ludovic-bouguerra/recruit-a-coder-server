package fr.ludovicbouguerra.ecodigo.result;

import java.io.Serializable;

public interface IResult extends Serializable{

	public String getInputData();
	public String getOutputData();
	public String getExpectedData();
	public boolean isValid();
	
}
