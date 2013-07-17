package fr.ludovicbouguerra.ecodigo.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import fr.ludovicbouguerra.ecodigo.controller.ControllerFactory;
import fr.ludovicbouguerra.ecodigo.controller.IController;


/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class Main {

	public static void main(String[] args){
		
		Logger log = Logger.getLogger(Main.class.getName());
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("config/config.ini"));

			final IController compilation = ControllerFactory.getInstance().createController(properties);
			
			/**
			 * Close correctly the application when the  
			 */
			Runtime.getRuntime().addShutdownHook(new Thread() {
			    public void run() {  
			    	
			    	compilation.shutdown();
			    }
			 });
			
			compilation.start();
		} catch (FileNotFoundException e) {
			log.severe("Can't find configuration file");
		} catch (IOException e) {
			log.severe("Can't find configuration file");
		}
		
	}
	
}
