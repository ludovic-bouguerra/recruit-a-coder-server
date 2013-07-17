package fr.ludovicbouguerra.ecodigo.controller;

import java.util.Properties;

public class ControllerFactory implements IControllerFactory{
	private static IControllerFactory instance;
	
	private ControllerFactory(){
		
	}
	
	
	public static IControllerFactory getInstance(){
		if (instance == null) {
			instance = new ControllerFactory();
		}
		return instance;
	}


	@Override
	public IController  createController(Properties properties) {
		CompilationController compilation = new CompilationController();
		compilation.init(properties);
		return compilation;
		
		
	}
}
