package fr.ludovicbouguerra.ecodigo.sourcecreator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SourceCreatorTest {

	private String code;
	private ISourceCreator sourceCreator;
	
	@Before
	public void init(){
		code = "test";
		sourceCreator = SourceCreatorFactory.getInstance().createSourceCreator("test", "java", code);
	}
	
	
	/**
	 * Todo Tester le contenu du fichier
	 */
	@Test
	public void testCreateFile(){
		sourceCreator.createFile();
		Assert.assertTrue(sourceCreator.getFile().exists());
		
		/**
		 * Lecture du fichier
		 */
		String chaine="";
		
		try{
			InputStream ips=new FileInputStream(sourceCreator.getFile()); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close();
			if (chaine.length() > 0){
				chaine = chaine.substring(0, chaine.length()-1);
			}
		}	
		catch (Exception e){
			Assert.fail();
		}
		
		Assert.assertEquals(code, chaine);
		
	}

	
}
