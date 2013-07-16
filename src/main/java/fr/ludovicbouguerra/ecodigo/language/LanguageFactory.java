package fr.ludovicbouguerra.ecodigo.language;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class LanguageFactory {
	private static LanguageFactory instance;
	
	private LanguageFactory(){
		
	}
	
	
	public ILanguage createJavaLanguage(){
		return new JavaLanguage();
	}
	
	public static LanguageFactory getInstance(){
		if (instance == null){
			instance = new LanguageFactory();
		}
		return instance;
	}
}
