package fr.ludovicbouguerra.ecodigo.messageprotocol;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;

import fr.ludovicbouguerra.ecodigo.language.LanguageFactory;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;
import fr.ludovicbouguerra.ecodigo.resultcomparator.ComparatorFactory;
import fr.ludovicbouguerra.ecodigo.resultcomparator.SourceComparatorFactory;

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
        	String inputData = message.getStringProperty("input-data");
        	String expectedResult = message.getStringProperty("expected-result");
        	logger.fine("End reading message variable");
        	System.out.println(code);

            
        	
        	String result = LanguageFactory.getInstance().createJavaLanguage().execute(code, inputData);
        	return SourceComparatorFactory.getInstance().createSourceComparator().compare(ComparatorFactory.getInstance().createEqualsComparator(), inputData, result, expectedResult);
        	
        }else{
        	throw new LanguageNotFound();	
        }
        
    }
}