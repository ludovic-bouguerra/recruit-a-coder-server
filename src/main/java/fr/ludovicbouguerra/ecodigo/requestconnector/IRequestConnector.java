package fr.ludovicbouguerra.ecodigo.requestconnector;

import java.util.Properties;

public interface IRequestConnector {

	public void init(Properties properties);
	public void listen();
	
	public void addRequestListener(IRequestListener requestListener);
}
