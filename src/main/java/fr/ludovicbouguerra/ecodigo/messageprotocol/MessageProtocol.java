package fr.ludovicbouguerra.ecodigo.messageprotocol;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;

import fr.ludovicbouguerra.ecodigo.language.LanguageFactory;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class MessageProtocol {
	private Logger logger = Logger.getLogger(getClass().getName());

    public String handleProtocolMessage(Message message) throws UnexpectedResult, JMSException, LanguageNotFound, ParametersException {
    	logger.fine("New message");
    	if (message.getStringProperty("language").equals("java")){
    		logger.fine("Reading message variable");
        	String code = message.getStringProperty("code");
        	Object inputData = message.getObjectProperty("input-data");
        	Object expectedResult = message.getObjectProperty("expected-result");
        	logger.fine("End reading message variable");
        	System.out.println(code);
        	System.out.println(Arrays.toString(((Collection<String>) inputData).toArray()));
        	System.out.println(Arrays.toString(((Collection<String>) expectedResult).toArray()));
            return LanguageFactory.getInstance().createJavaLanguage().execute(code, (Collection<String>)inputData, (Collection<String>)expectedResult); 	
        }else{
        	throw new LanguageNotFound();	
        }
        
    }
}