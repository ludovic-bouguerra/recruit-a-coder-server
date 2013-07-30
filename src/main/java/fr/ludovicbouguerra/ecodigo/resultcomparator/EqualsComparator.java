package fr.ludovicbouguerra.ecodigo.resultcomparator;

/**
 * Compare deux chaines de caractères vérifie quelles soient
 * égale. (Avec la possibilité d'ajouter un saut de ligne en fin)
 * 
 * Par exemple : 
 * 
 * blablalbab\n
 * blablalbab
 * 
 * sont équivalents
 * 
 * blablabla
 * blablabla
 * 
 * Le sont aussi
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
		return expected.equals(actual) || expected.equals(actual+"\n");
	}

}
