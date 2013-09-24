package fr.ludovicbouguerra.ecodigo.language;

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
	public String execute(String code, String inputData) throws UnexpectedResult;
	
}
