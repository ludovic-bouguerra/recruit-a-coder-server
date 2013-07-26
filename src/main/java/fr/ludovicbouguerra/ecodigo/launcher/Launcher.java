package fr.ludovicbouguerra.ecodigo.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class Launcher implements Callable<Boolean>{

	private static Logger logger = Logger.getLogger(Launcher.class.getName());
	
	private Process process;
	private ArrayList<String> parameters;
	private String errorResponse;
	private String response;
	private String inputData;
	private int timeout;
	
	public Launcher(ArrayList<String> parameters, int timeout){
		
		this.parameters = parameters;
		this.timeout = timeout;
	}
	
	public Launcher(ArrayList<String> parameters, int timeout, String inputData){
		this(parameters, timeout);
		this.inputData = inputData;
		
	}
	
	public void execute() throws UnexpectedResult, TimeoutException{

        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<Boolean> future = executor.submit(this);
        try {
        	if (timeout != -1){
        		future.get(timeout, TimeUnit.SECONDS);	
        	}else {
        		future.get();
        	}
			
		} catch (InterruptedException e) {
			throw new UnexpectedResult(e.getMessage());
		} catch (ExecutionException e) {
			throw new UnexpectedResult(e.getMessage());
			
		}finally{
			process.destroy();
			executor.shutdownNow();
		}
		
	}
	
	
	public String getErrorResponse(){
		return errorResponse;
	}
	
	public String getResponse(){
		return response;
	}

	@Override
	public Boolean call() throws Exception {
		
		logger.fine("launching compilation");
		ProcessBuilder builder = new ProcessBuilder(parameters);		

		process = builder.start();
		
		StreamParser esp = new StreamParser(process.getErrorStream());
		esp.start();
		StreamParser isp = new StreamParser(process.getInputStream());
		isp.start();
		
		
		
		if(inputData != null){
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream());
			outputStreamWriter.write(inputData);
			outputStreamWriter.flush();
			outputStreamWriter.close();		
		}
		process.waitFor();
		
		errorResponse = esp.getResult();
		response = isp.getResult();
		logger.fine("ending compilation");
		System.out.println("ok");
		
		return true;
	}
	
}


class StreamParser extends Thread{
	private String result;
	private InputStream inputStream;
	
	public StreamParser(InputStream is){
		this.inputStream = is;
	}
	
	public void run(){
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		result = "";
		String line;
		try {
			while ((line = br.readLine ()) != null) {
			    result += line + "\n";
			}
			if (result.length() > 1)
				result = result.substring(0, result.length()-1);
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getResult(){
		return result;
	}
	
}
