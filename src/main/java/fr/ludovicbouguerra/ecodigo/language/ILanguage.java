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
	 * @param code : Code à exécuter
	 */
	public String execute(String code, String inputData, String expectedResult) throws UnexpectedResult;
	
}
