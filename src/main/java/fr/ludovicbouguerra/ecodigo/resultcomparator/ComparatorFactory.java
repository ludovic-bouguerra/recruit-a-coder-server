package fr.ludovicbouguerra.ecodigo.resultcomparator;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class ComparatorFactory implements IComparatorFactory{

	private static IComparatorFactory instance;
	
	private ComparatorFactory() {
		
	}
	
	@Override
	public IComparator createEqualsComparator() 
	{
		return new EqualsComparator();
	}
	
	public static IComparatorFactory getInstance(){
		if (instance == null){
			instance = new ComparatorFactory();
		}
		return instance;
	}
	
}