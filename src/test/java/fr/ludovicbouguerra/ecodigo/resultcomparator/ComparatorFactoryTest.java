package fr.ludovicbouguerra.ecodigo.resultcomparator;

import org.junit.Assert;
import org.junit.Test;

public class ComparatorFactoryTest {
	
	@Test
	public void testGetInstance(){
		IComparatorFactory ic = ComparatorFactory.getInstance();
		Assert.assertEquals(ic, ComparatorFactory.getInstance());
	}

	@Test
	public void testCreateEqualsComparator(){
		IComparator ic = ComparatorFactory.getInstance().createEqualsComparator();
		Assert.assertNotNull(ic);
	}
	
}
