package fr.ludovicbouguerra.ecodigo.sourcecreator;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class SourceCreatorFactory implements ISourceCreatorFactory{
	private static ISourceCreatorFactory instance;
	
	private SourceCreatorFactory(){
		
	}
	
	public ISourceCreator createSourceCreator(String name, String extension, String code){
		return new SourceCreator(name, extension, code);
	}
	
	public static ISourceCreatorFactory getInstance(){
		if (instance == null){
			instance = new SourceCreatorFactory();
		}
		return instance;
	}
}
