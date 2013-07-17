package fr.ludovicbouguerra.ecodigo.controller;

import java.util.Properties;

public interface IController {
	public void init(Properties properties);
	
	public void start();
	
	public void shutdown();
}
