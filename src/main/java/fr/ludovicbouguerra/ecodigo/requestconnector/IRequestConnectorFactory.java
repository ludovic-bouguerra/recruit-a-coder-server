package fr.ludovicbouguerra.ecodigo.requestconnector;

import java.util.Properties;

public interface IRequestConnectorFactory {
	
	IRequestConnector createRequestListener(Properties properties);
	
}
