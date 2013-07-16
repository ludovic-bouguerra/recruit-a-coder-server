package fr.ludovicbouguerra.ecodigo.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fr.ludovicbouguerra.ecodigo.controller.CompilationController;


/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class Main {

	public static void main(String[] args){
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("config/config.ini"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CompilationController compilation = new CompilationController();
		
		
	}
	
}
