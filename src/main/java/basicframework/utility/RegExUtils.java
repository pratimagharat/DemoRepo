package basicframework.utility;

import java.util.regex.Pattern;

public class RegExUtils {
	
	// this is just example
	public static final String NONKEYBOARD_CHARACTER_PATTERN=
			"(([^/\\\\a-zA-Z0-9~`!@#$%^&*()-_=+|\\[\\]:'{};\"?/>.<, \n\r]))";
	
	public static boolean findMatchBasedOnRegEx(String values){
		
		Pattern paterCompile= Pattern.compile(NONKEYBOARD_CHARACTER_PATTERN);
		boolean matchfound=false;
		if(values != null){
			matchfound= paterCompile.matcher(values).find();
			
			if(matchfound == true){
				return matchfound;
			}
		}
		return matchfound;
	}

	public static String removeSoftwareSpecialCharacter(String values){
		return values.replaceAll(NONKEYBOARD_CHARACTER_PATTERN, "").trim();
	}
	
}
