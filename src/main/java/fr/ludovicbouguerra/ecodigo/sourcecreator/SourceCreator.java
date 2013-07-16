package fr.ludovicbouguerra.ecodigo.sourcecreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.common.io.Files;

/**
 * 
 * @author Ludovic Bouguerra <bouguerra.ludovic@gmail.com>
 * 
 *
 */
public class SourceCreator implements ISourceCreator{
	
	private File file;
	private String code;
	private String name;
	private String extension;
	
	public SourceCreator(){
		
	}
	
	public SourceCreator(String name, String extension, String code){
		this.extension = extension;
		this.code = code;
		this.name = name;
	}
	
	public void createFile(){
		
		
			File parentPath = Files.createTempDir();
			
			file = new File(parentPath, name +"."+extension); 
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(new FileWriter(file));
				writer.write( code);

			}
			catch ( IOException e)
			{
			}
			finally
			{
				try
				{
					if ( writer != null)
						writer.close( );
				}
				catch ( IOException e)
				{
				}
		     }
		
	}
	
	public File getFile(){
		return file;
	}
	
}
