package org.vaadin.thomas.devday.memleaks;

import java.util.Random;

public class DataHogger {

	private final String data;

	private static final String characters = "qwertyuiopåasdfghjklöäzxcvbnm";

	public DataHogger() {

		// a meg per object? sure.
		data = generateString(new Random(), characters, 1000 * 1000);
	}

	public String getData() {
		return data.substring(0, 100);
	}

	public static String generateString(Random rng, String characters, int length) {
		final char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
}