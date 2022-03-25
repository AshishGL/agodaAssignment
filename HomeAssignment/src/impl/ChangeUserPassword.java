package impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUserPassword {

	public static void main(String[] args) {

	 //old Password is harcoded as Ashish@*12345abcdefghi
		//System.out.println(changePassword("Ashish@*12345abcdefghi", "Assshis@*12345huyti"));
		System.out.println(changePassword("Ashish@*12345abcdefghi", "Ashish@*12345abcdefghi"));


	}

	public static boolean changePassword(String oldPassword, String newPassword) {
       boolean flag=false;
		
       if(oldPassword.contentEquals(newPassword))
       {
    	   System.out.println("New Password cannot be same as Old Password!!");
    	   return false;
       }
		if(verifyOldPassword(oldPassword))
		{
			if(isValidPassword(newPassword)) {
				System.out.println("Password Updated Successfully!");
			flag=true;
			}
		}
		
		else
			System.out.println("Old Password is Incorrect!!!");
		
		
		return flag;
	}

	public static boolean verifyOldPassword(String oldPassword) {

		if (oldPassword.contentEquals("Ashish@*12345abcdefghi"))
			return true;

		else
			return false;
	}

	private static char[] generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$&*";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password;
	}

	public static boolean isValidPassword(String password) {

		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[!@#$&*])" + "(?=\\S+$).{18,}$";

		// String regexSpecialCharsNotAllowed="^(?=.*[%^*)(:}{;'/.,<>~`+=_-|])";
		String regexSpecialCharsNotAllowed = "(?=.*[)(])";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		Pattern p1 = Pattern.compile(regexSpecialCharsNotAllowed);

		char[] pass = password.toCharArray();

		// If the password is empty
		// return false
		if (password == null) {
			return false;
		}

		int letters = 0, digits = 0, specialChars = 0;

		for (int i = 0; i < pass.length; i++) {
			char ch = pass[i];
			if (Character.isLetter(ch)) {
				letters++;
			} else if (Character.isDigit(ch)) {
				digits++;
			} else if (isSpecialChar(ch)) {
				specialChars++;
			}
		}

		// Pattern class contains matcher() method
		// to find matching between given password
		// and regular expression.
		Matcher m = p.matcher(password);
		Matcher m1 = p1.matcher(password);

		if (m.matches() == true && specialChars <= 4 && isPasswordHaveMoreThanFiftyPercentNumbers(password) == true
				&& charFreq(password) == true)
			return true;

		// Return if the password
		// matched the ReGex
		else {
			return false;
		}
	}

	// checking that character is special or not
	static boolean isSpecialChar(char c) {
		switch (c) {
		case '@':
		case '#':
		case '$':
		case '!':
		case '*':
		case '&':

			return true;
		default:
			return false;
		}
	}

	static boolean isPasswordHaveMoreThanFiftyPercentNumbers(String password) {

		int length = password.length();
		int numbersCount = 0;

		for (int i = 0; i < length; i++) {
			if (Character.isDigit(password.charAt(i)))
				numbersCount++;
		}
//		System.out.println("");
//		System.out.println("Total number of Characters in Password are :" + length);
//		System.out.println("Total number of Numbers in Password are :" + numbersCount);

		if (numbersCount >= length / 2) {
			System.out.println("50 % of password should not be a number");
			return false;
		} else
			return true;

	}

	// Get the frequency of each character in the string
	/**
	 * @param s
	 * @return
	 */
	public static boolean charFreq(String s) {

		// Store all characters and
		// their frequencies in dictionary
		Map<Character, Integer> d = new HashMap<Character, Integer>();
		boolean flag = true;

		for (int i = 0; i < s.length(); i++) {
			if (d.containsKey(s.charAt(i))) {
				d.put(s.charAt(i), d.get(s.charAt(i)) + 1);
			} else {
				d.put(s.charAt(i), 1);
			}
		}

		// Print characters and their
		// frequencies in same order
		// of their appearance
		for (Integer name : d.values()) {
			if (name > 4) {
				flag = false;
				System.out.println("No duplicate repeat characters more than 4");
				return flag;
			}

		}
		return flag;

	}

}
