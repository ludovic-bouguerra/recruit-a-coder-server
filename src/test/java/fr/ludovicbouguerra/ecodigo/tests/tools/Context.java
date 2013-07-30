package fr.ludovicbouguerra.ecodigo.tests.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Context implements IContext{
	
	private static IContext instance;
	private Properties properties;
	
	private Context() throws ContextException{
		properties = new Properties();
		try {
			properties.load(new FileInputStream(Class.class.getResource("/config.ini").getFile()));
		} catch (FileNotFoundException e) {
			throw new ContextException(e.getMessage());
		} catch (IOException e) {
			throw new ContextException(e.getMessage());
		}
		
	}
	
	@Override
	public Properties getConfiguration() {
		return properties;
	}
	
	public static IContext getInstance() throws ContextException{
		if(instance == null){
			instance = new Context();
		}
		return instance;
	}
	
	
}
