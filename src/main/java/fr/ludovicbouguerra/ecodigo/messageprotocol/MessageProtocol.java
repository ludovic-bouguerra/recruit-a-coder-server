package fr.ludovicbouguerra.ecodigo.messageprotocol;

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
    public String handleProtocolMessage(Message message) throws UnexpectedResult, JMSException, LanguageNotFound {
        if (message.getStringProperty("language").equals("java")){
        	String code = message.getStringProperty("code");
        	String inputData = message.getStringProperty("input-data");
        	String expectedResult = message.getStringProperty("expected-result");
        	return LanguageFactory.getInstance().createJavaLanguage().execute(code, inputData, expectedResult); 	
        }else{
        	throw new LanguageNotFound();	
        }
        
    }
}