package fr.ludovicbouguerra.ecodigo.sourcecreator;

public interface ISourceCreatorFactory {
	
	public ISourceCreator createSourceCreator(String name, String extension, String code);
	
}
