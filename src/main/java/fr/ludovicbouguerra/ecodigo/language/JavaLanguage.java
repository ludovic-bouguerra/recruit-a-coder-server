package fr.ludovicbouguerra.ecodigo.language;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import fr.ludovicbouguerra.ecodigo.launcher.Launcher;
import fr.ludovicbouguerra.ecodigo.resultcomparator.ComparatorFactory;
import fr.ludovicbouguerra.ecodigo.sourcecreator.ISourceCreator;
import fr.ludovicbouguerra.ecodigo.sourcecreator.SourceCreatorFactory;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class JavaLanguage implements ILanguage{

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public String execute(String code, Collection<String> inputData, Collection<String> expectedResult) throws UnexpectedResult {
		logger.fine("Compilation code JAVA" + "Code " + code + "Expected Result "+ expectedResult);
		String compilationLogs = "";
		
        ISourceCreator source = SourceCreatorFactory.getInstance().createSourceCreator("Test","java", code);
        source.createFile();
        File pathName = source.getFile();
        try {
			
			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add("/usr/bin/javac");
			parameters.add(pathName.getPath());
		
			logger.fine("Compilation logs "+ compilationLogs);
			Launcher launcherCompilation = new Launcher(parameters, 5);
			launcherCompilation.execute();
			
			compilationLogs += launcherCompilation.getErrorResponse();
			compilationLogs += launcherCompilation.getResponse();
			
			logger.fine("Compilation logs "+ compilationLogs);
			
			
			ArrayList<String> parametersJava = new ArrayList<String>();
			parametersJava.add("/usr/bin/java");
			parametersJava.add("-Djava.security.manager");
			parametersJava.add("-Djava.security.policy=config/java/security.properties");
			parametersJava.add("-Xmx10m");
			parametersJava.add("-classpath");
			parametersJava.add(pathName.getParent()+File.separatorChar);
			parametersJava.add("Test");
			
			
			for (int i = 0; i < inputData.size(); i++) {
				Launcher l = new Launcher(parametersJava, 5, (String) inputData.toArray()[i]);
				l.execute();
				
				if (!ComparatorFactory.getInstance().createEqualsComparator().compare((String) expectedResult.toArray()[i], l.getResponse())){
					
					throw new UnexpectedResult(l.getErrorResponse() + l.getResponse());
				}
			}

			return compilationLogs;
			
		} catch (TimeoutException e) {
			throw new UnexpectedResult("Timeout Exception : "+e.getMessage()); 
			
		}			
		

	}

}
