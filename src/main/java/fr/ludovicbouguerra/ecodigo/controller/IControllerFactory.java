package fr.ludovicbouguerra.ecodigo.controller;

import java.util.Properties;

public interface IControllerFactory {
	IController createController(Properties properties);
}
