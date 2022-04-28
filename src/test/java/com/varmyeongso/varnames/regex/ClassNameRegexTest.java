package com.varmyeongso.varnames.regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClassNameRegexTest {

	public final String classLineRegex = "^[a-zA-Z0-9]+\\s+[class | interface | enum]+\\s+[a-zA-Z0-9_]+\\s+\\{+$";
	public final String classNameRegex = "\\w+";

	@Test
	void classNameTest() {
		//given
		String code = makeSample();
		List<String> codeByLine = splitCodeByLine(code);
		String result = "";

		//when
		Pattern regex = Pattern.compile(classNameRegex);
		for (String line : codeByLine) {
			if (Pattern.matches(classLineRegex, line)) {
				Matcher matcher = regex.matcher(line);
				while (matcher.find()) {
					result = matcher.group();
					System.out.println("result = " + result);
				}
				break;
			}
		}
		//then
		Assertions.assertThat(result).isEqualTo("ClassNameRegexTest");
	}

	private String makeSample() {
		return "package com.varmyeongso.varnames.regex;\n"
			+ "\n"
			+ "import java.util.regex.Matcher;\n"
			+ "import java.util.regex.Pattern;\n"
			+ "import org.assertj.core.api.Assertions;\n"
			+ "import org.junit.jupiter.api.Test;\n"
			+ "\n"
			+ "public class ClassNameRegexTest {\n"
			+ "\n"
			+ "\tpublic final String classLineRegex = \"^[a-zA-Z0-9]+\\\\s+[class | interface | enum]+\\\\s+[a-zA-Z0-9_]+\\\\s+\\\\{+$\";\n"
			+ "\tpublic final String classNameRegex = \"\\\\w+\";\n"
			+ "\n"
			+ "\t@Test\n"
			+ "\tvoid classNameTest() {\n"
			+ "\n"
			+ "\n"
			+ "\t\t//given\n"
			+ "\t\tString line = \"public class ClassNameRegexTest {\";\n"
			+ "\t\tString result = \"\";\n"
			+ "\n"
			+ "\t\tboolean matches = Pattern.matches(classLineRegex, line);\n"
			+ "\n"
			+ "\t\tSystem.out.println(\"matches = \" + matches);\n"
			+ "\n"
			+ "\t\t//when\n"
			+ "//\t\tif (Pattern.matches(classLineRegex, line)) {\n"
			+ "//\t\t\tPattern regex = Pattern.compile(classNameRegex);\n"
			+ "//\t\t\tMatcher matcher = regex.matcher(line);\n"
			+ "//\t\t}\n"
			+ "\t\tPattern regex = Pattern.compile(classNameRegex);\n"
			+ "\t\tMatcher matcher = regex.matcher(line);\n"
			+ "\t\twhile (matcher.find()) {\n"
			+ "\t\t\tresult = matcher.group();\n"
			+ "\t\t\tSystem.out.println(\"result = \" + result);\n"
			+ "\t\t}\n"
			+ "\t\t//then\n"
			+ "\t\tAssertions.assertThat(result).isEqualTo(\"ClassNameRegexTest\");\n"
			+ "\n"
			+ "\t}\n"
			+ "}\n";
	}

	private List<String> splitCodeByLine(String code) {
		return List.of(code.split("\n"));
	}
}