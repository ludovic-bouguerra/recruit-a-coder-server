package fr.ludovicbouguerra.ecodigo.resultcomparator;

public class SourceComparatorFactory implements ISourceComparatorFactory{

	private static ISourceComparatorFactory instance;
	
	@Override
	public ISourceComparator createSourceComparator() {
		return new SourceComparator();
	}
	
	
	public static ISourceComparatorFactory getInstance(){
		if(instance == null){
			instance = new SourceComparatorFactory();
		}
		return instance;
	}

}
