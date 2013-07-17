package fr.ludovicbouguerra.ecodigo.controller;

import java.util.Properties;

import fr.ludovicbouguerra.ecodigo.requestconnector.IRequestConnector;
import fr.ludovicbouguerra.ecodigo.requestconnector.RequestConnectorFactory;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class CompilationController implements IController{
	
	private Properties properties;

	protected CompilationController(){
		
	}
	
	public void init(Properties properties){
		this.properties = properties;
	}
	
	public void start(){
		
		IRequestConnector requestListener = RequestConnectorFactory.getInstance().createRequestListener(properties);
		requestListener.listen();
		
		
	}
	
	public void shutdown(){
		
	}
}
