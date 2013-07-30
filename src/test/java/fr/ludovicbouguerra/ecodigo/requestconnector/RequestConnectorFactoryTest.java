package fr.ludovicbouguerra.ecodigo.requestconnector;


import org.junit.Assert;
import org.junit.Test;

import fr.ludovicbouguerra.ecodigo.tests.tools.Context;
import fr.ludovicbouguerra.ecodigo.tests.tools.ContextException;

public class RequestConnectorFactoryTest {
	
	@Test
	public void testGetInstance(){
		IRequestConnectorFactory ir = RequestConnectorFactory.getInstance();
		Assert.assertEquals(ir, RequestConnectorFactory.getInstance());
	}
	
	@Test
	public void testCreateJMSRequestConnector() throws ContextException{
		IRequestConnectorFactory ir = RequestConnectorFactory.getInstance();
		ir.createRequestListener(Context.getInstance().getConfiguration());
	}
	
}
