package com.example.demo;

import java.util.*;
import java.util.regex.Pattern;

public class DemoApplication {

	public static void main(String[] args) {

		System.out.println("\n------------------------------------");
		System.out.print("Frequency compression: ");
		System.out.print(compressStr("aabbbcmdddannb"));

		System.out.println("\n------------------------------------");
		System.out.print("Longest substring no repeat: ");
		System.out.print(longestSubstr("xyzcck12345666"));

		System.out.println("\n------------------------------------");
		System.out.print("Group anagrams array: ");
		groupAnagrams(new String[]{"eat", "tea", "", "tan", "ate", "nat", "aet", "bat"});

		System.out.println("\n------------------------------------");
		System.out.print("Check Rotation string: " + checkRotation("helloworld", "lloworldhe"));

		System.out.println("\n------------------------------------");
		System.out.print("Longest Palindromic string: " + longestPalindromic("aCcaXXacba"));

		System.out.println("\n------------------------------------");
		System.out.print("Top word frequency: " + wordFrequency("Java is great. Java is powerful. Python is great too. ALL GREAT!"));

		System.out.println("\n------------------------------------");
	}


	public static String compressStr(String str) {
		str += "#";
		String output = "";
		int counter = 1;

		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) == str.charAt(i+1)) {
				counter++;
			}
			else {
				output += str.charAt(i) + Integer.toString(counter);
				counter = 1;
			}
		}

		return output;
	}

	public static String longestSubstr(String str) {
		int beginIndex = 0;
		int endIndex = 0;
		String output = "";

		if (str.length() == 1) {
			return str;
		}

		for (int i = 0; i <str.length() -1; i++) {
			if (str.charAt(i) == str.charAt(i+1)) {
				beginIndex = endIndex = i + 1;
			} else {
				endIndex++;
			}
			String currSubstring = str.substring(beginIndex, endIndex+1);
			output = output.length() > currSubstring.length() ? output : currSubstring;
		}

		return output;
	}

	public static void groupAnagrams(String[] wordsArray) {
		Map<String, List<String>> anagramsMap = new HashMap<>();

		for (String word: wordsArray) {
			if (word.isEmpty()) {
				continue;
			}

			char[] wordCharArr = word.toCharArray();
			Arrays.sort(wordCharArr);
			String sortedWord = new String(wordCharArr);

			if (anagramsMap.containsKey(sortedWord)) {
				anagramsMap.get(sortedWord).add(word);
			} else {
				anagramsMap.put(sortedWord, new ArrayList<>(List.of(word)));
			}
		}
		System.out.print(anagramsMap);
	}

	private static boolean checkRotation(String str, String target) {
		boolean valid = false;
		String rotatedStr = str;

		for (int i = 0; i < str.length() && !valid; i++) {
			rotatedStr = String.valueOf(rotatedStr.charAt(str.length()-1)) + rotatedStr.substring(0, str.length()-1);
			valid = rotatedStr.equals(target);
		}

		return valid;
	}

	private static String longestPalindromic(String str) {
		String output = "";
		boolean isPalindrome = true;
		boolean evenLenth = str.length() % 2 == 0;
		int midIndex = evenLenth
				? (str.length() / 2) -1
				: (int) Math.floor((double) str.length() / 2);

		for (int i = midIndex; i > -1 && isPalindrome; i--) {
			int diffIndex = midIndex - i;
			String midStr = evenLenth
					? str.substring(midIndex - diffIndex, midIndex + diffIndex + 2)
					: str.substring(midIndex - diffIndex, midIndex + diffIndex + 1);
			String reversedStr = new StringBuilder(midStr).reverse().toString();
			isPalindrome = midStr.equals(reversedStr);
			output = isPalindrome ? midStr : output;
		}

		return output.length() > 1 ? output : "None found.";
	}

	public static Map<String, Integer> wordFrequency(String str) {
		Map<String, Integer> wordCountMap = new HashMap<>();

		String [] strArray = str.replaceAll("[.,!:$@#(){}]", "").toLowerCase().split(" ");
		//System.out.println("strArray: " + Arrays.toString(strArray));
		for (String s : strArray) {
			wordCountMap.put(s, wordCountMap.containsKey(s) ? wordCountMap.get(s) + 1 : 1);
		}

		int maxCounter = Collections.max(wordCountMap.values());
		Map<String, Integer> result = new HashMap<>();
		for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
			if (entry.getValue() == maxCounter) {
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}
}
