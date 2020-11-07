package ru.itis.words.utils;

public class Replacer {
	public static String replace(String text, String source, String target) {
		return text.replaceAll(source, target);
	}
}