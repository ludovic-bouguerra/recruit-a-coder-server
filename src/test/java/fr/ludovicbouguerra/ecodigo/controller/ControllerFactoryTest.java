package fr.ludovicbouguerra.ecodigo.controller;

import org.junit.Assert;
import org.junit.Test;

import fr.ludovicbouguerra.ecodigo.tests.tools.Context;
import fr.ludovicbouguerra.ecodigo.tests.tools.ContextException;

public class ControllerFactoryTest {

	
	/**
	 * Test appel successif de getInstance de controller Factory 
	 * retourne bien la même référence.
	 */
	@Test
	public void testGetInstance(){
		IControllerFactory ic = ControllerFactory.getInstance();
		Assert.assertNotNull(ic);
		Assert.assertEquals(ic, ControllerFactory.getInstance());
	}
	
	/**
	 * Test création d'un controller
	 * @throws ContextException 
	 */
	@Test
	public void testCreateController() throws ContextException{
		IControllerFactory ic = ControllerFactory.getInstance();
		IController controller = ic.createController(Context.getInstance().getConfiguration());
		Assert.assertTrue(controller instanceof CompilationController);
		Assert.assertNotNull(controller);
		
	}
	
}
