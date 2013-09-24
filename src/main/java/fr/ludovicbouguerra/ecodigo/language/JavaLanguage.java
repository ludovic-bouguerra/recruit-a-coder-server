package fr.ludovicbouguerra.ecodigo.language;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import fr.ludovicbouguerra.ecodigo.launcher.Launcher;
import fr.ludovicbouguerra.ecodigo.sourcecreator.ISourceCreator;
import fr.ludovicbouguerra.ecodigo.sourcecreator.SourceCreatorFactory;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 * 
 */
public class JavaLanguage implements ILanguage {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public String execute(String code, String inputData)
			throws UnexpectedResult {
		logger.fine("Compilation code JAVA" + "Code " + code);
		String compilationLogs = "";

		ISourceCreator source = SourceCreatorFactory.getInstance()
				.createSourceCreator("Test", "java", code);
		source.createFile();
		File pathName = source.getFile();
		try {

			ArrayList<String> parameters = new ArrayList<String>();
			parameters.add("/usr/bin/javac");
			parameters.add(pathName.getPath());

			logger.fine("Compilation logs " + compilationLogs);
			Launcher launcherCompilation = new Launcher(parameters, 5);
			launcherCompilation.execute();

			compilationLogs += launcherCompilation.getErrorResponse();
			compilationLogs += launcherCompilation.getResponse();

			logger.fine("Compilation logs " + compilationLogs);

			System.out.println(launcherCompilation.getErrorResponse());
			System.out.println(launcherCompilation.getResponse());
			System.out.println(compilationLogs);

			if (!launcherCompilation.getErrorResponse().equals("")) {
				throw new UnexpectedResult(
						launcherCompilation.getErrorResponse());
			} else {

				ArrayList<String> parametersJava = new ArrayList<String>();
				parametersJava.add("/usr/bin/java");
				parametersJava.add("-Djava.security.manager");
				parametersJava
						.add("-Djava.security.policy=config/java/security.properties");
				parametersJava.add("-Xmx10m");
				parametersJava.add("-classpath");
				parametersJava.add(pathName.getParent() + File.separatorChar);
				parametersJava.add("Test");


				Launcher launcherCode = new Launcher(parametersJava, 5, inputData);
				launcherCode.execute();
				
				compilationLogs += launcherCode.getResponse();
				compilationLogs += launcherCode.getErrorResponse();

				System.out.println("launcherCode Response : "+launcherCode.getResponse());

				System.out.println(compilationLogs);

				// -- Si il y a une erreur à la compilation --compilationLogs;
				return compilationLogs;
			}
		} catch (TimeoutException e) {
			throw new UnexpectedResult("Timeout Exception : " + e.getMessage());

		}

	}

}
