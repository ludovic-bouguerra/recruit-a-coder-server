package fr.ludovicbouguerra.ecodigo.resultcomparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EqualsComparatorTest {
	
	private IComparator comparator;
	
	@Before
	public void init(){
		comparator = ComparatorFactory.getInstance().createEqualsComparator();
	}
	
	@Test
	public void testStrictEquals(){
		String expected = "aaaaaa\n";
		String result = "aaaaaa\n";
		Assert.assertTrue(comparator.compare(expected, result));
		
		
	}
	
	@Test
	public void testInequal(){
		String expected = "aaaaaa\n";
		String result = "aaaaaavrge\n";
		Assert.assertEquals(false, comparator.compare(expected, result));
	}
	
	@Test
	public void testEqualsLine(){
		String expected = "aaaaaa\n";
		String result = "aaaaaa";
		Assert.assertTrue(comparator.compare(expected, result));

	}
	
}
