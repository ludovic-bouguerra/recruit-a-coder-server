package fr.ludovicbouguerra.ecodigo.resultcomparator;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class EqualsComparator implements IComparator{

	@Override
	public boolean compare(String expected, String actual) {
		System.out.println("result compare");
		System.out.println(expected);
		System.out.println(actual);
		return expected.equals(actual);
	}

}
