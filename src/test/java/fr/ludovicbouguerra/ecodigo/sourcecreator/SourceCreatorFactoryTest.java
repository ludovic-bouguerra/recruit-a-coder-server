package fr.ludovicbouguerra.ecodigo.sourcecreator;


import org.junit.Assert;
import org.junit.Test;

public class SourceCreatorFactoryTest {

	@Test
	public void testGetInstance(){
		ISourceCreatorFactory source = SourceCreatorFactory.getInstance();
		Assert.assertEquals(source, SourceCreatorFactory.getInstance());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testCreateSourceCreator(){
		ISourceCreator s = SourceCreatorFactory.getInstance().createSourceCreator("test", "java", "aaa");
		Assert.assertNotNull(s);
	}
	
}
