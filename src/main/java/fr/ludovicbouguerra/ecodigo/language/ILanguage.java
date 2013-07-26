package fr.ludovicbouguerra.ecodigo.language;

import java.util.Collection;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public interface ILanguage {

	/**
	 * 
	 * @param code : Code a executer
	 */
	public String execute(String code, Collection<String> inputData, Collection<String> expectedResult) throws UnexpectedResult;
	
}
