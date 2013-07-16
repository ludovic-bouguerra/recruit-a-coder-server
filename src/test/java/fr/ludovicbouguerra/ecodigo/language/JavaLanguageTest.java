package fr.ludovicbouguerra.ecodigo.language;

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
		language.execute(codigo, "test", "Salut\ntest\n");
	}
	
}
