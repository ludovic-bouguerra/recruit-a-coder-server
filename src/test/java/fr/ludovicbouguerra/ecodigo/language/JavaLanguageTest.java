package fr.ludovicbouguerra.ecodigo.language;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import fr.ludovicbouguerra.ecodigo.language.ILanguage;
import fr.ludovicbouguerra.ecodigo.language.JavaLanguage;
import fr.ludovicbouguerra.ecodigo.language.UnexpectedResult;

public class JavaLanguageTest {
	
	@Test
	public void testExecute() throws UnexpectedResult{
		
		ILanguage language = new JavaLanguage();
		String codigo = "import java.io.BufferedReader; \n"+
						"import java.io.InputStreamReader; \n"+
						"import java.io.IOException; \n"+
						"public class Test{\n"+
						"\n"+
						"	public static void main(String[] args) throws IOException{\n"+
						"\n"+	
						"System.out.println(\"Salut\");\n"+
						"BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n"+
						"String line;\n"+
						"String result = \"\";\n"+
						"while ((line = br.readLine()) != null){\n"+
									"result += line;\n"+
								"}\n"+
								"System.out.println(result);\n"+
							"}\n"+
						"}\n";
		
		language.execute(codigo, "test");
	}
	

	
	@Test
	public void testSecurityFile(){
		
		ILanguage language = new JavaLanguage();
		String codigo = "import java.io.BufferedReader; \n"+
						"import java.io.InputStreamReader; \n"+
						"import java.io.IOException; \n"+
						"import java.io.File; \n"+
						"public class Test{\n"+
						"\n"+
						"	public static void main(String[] args) throws IOException{\n"+
						"\n"+
						"new File(\"/User/test\").delete();\n"+
									
								
						
							"}\n"+
						"}\n";
		
		Collection<String> input = new ArrayList<String>();
		input.add("test");
		
		Collection<String> expected = new ArrayList<String>();
		expected.add("Salut\ntest\n");
		try{
			language.execute(codigo, "test");
		}catch(UnexpectedResult e){
		}
	}
	
	
	@Test
	public void testTimeout(){
		
		ILanguage language = new JavaLanguage();
		String codigo = "import java.io.BufferedReader; \n"+
						"import java.io.InputStreamReader; \n"+
						"import java.io.IOException; \n"+
						"public class Test{\n"+
						"\n"+
						"	public static void main(String[] args) throws IOException{\n"+
						"\n"+
						"while (true){\n"+
									
								"}\n"+
						
							"}\n"+
						"}\n";

		try{
			language.execute(codigo, "test");
		}catch(UnexpectedResult e){
			Assert.assertEquals("Timeout Exception : null", e.getMessage());
		}
	}
	
	
	@Test
	public void testCompilationError(){
		
		ILanguage language = new JavaLanguage();
		String codigo = "\n";
		
		try{
			language.execute(codigo, "test");
			//Assert.fail();
		}catch(UnexpectedResult e){
			Assert.assertEquals("Timeout Exception : null", e.getMessage());
		}
	}
	
}
