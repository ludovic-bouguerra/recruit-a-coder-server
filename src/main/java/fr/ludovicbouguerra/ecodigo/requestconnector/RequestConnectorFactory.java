package fr.ludovicbouguerra.ecodigo.requestconnector;

import java.util.Properties;

public class RequestConnectorFactory implements IRequestConnectorFactory{

	private static IRequestConnectorFactory instance;
	
	private RequestConnectorFactory(){
		
	}
	
	@Override
	public IRequestConnector createRequestListener(Properties properties) {
		IRequestConnector ir = new JMSRequestConnector();
		ir.init(properties);
		return ir;
	}
	
	public static IRequestConnectorFactory getInstance(){
		if(instance == null){
			instance = new RequestConnectorFactory();
		}
		return instance;
	}

}
