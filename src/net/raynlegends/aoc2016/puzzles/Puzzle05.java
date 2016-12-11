package net.raynlegends.aoc2016.puzzles;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Puzzle05 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		StringBuilder password = new StringBuilder();

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "NoSuchAlgorithmException";
		}

		int i = 0;
		while (password.length() < 8) {
			String hash = hash(md5, input + i++);
			if (hash.startsWith("00000")) {
				password.append(hash.charAt(5));
			}
		}

		return password.toString();
	}

	@Override
	public String calculatePart2(String input) {
		String[] password = new String[8];
		int found = 0;

		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "NoSuchAlgorithmException";
		}

		int i = 0;
		while (found < 8) {
			String hash = hash(md5, input + i++);
			if (!hash.startsWith("00000")) {
				continue;
			}

			int position = Integer.parseInt(hash.charAt(5) + "", 16);

			if (position >= password.length) {
				continue;
			}

			if (password[position] == null) {
				password[position] = hash.charAt(6) + "";
				found++;
			}
		}

		StringBuilder builder = new StringBuilder();
		for (String character : password) {
			builder.append(character);
		}
		return builder.toString();
	}

	private final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	private String hash(MessageDigest digest, String string) {
		try {
			digest.update(string.getBytes("UTF-8"));
			byte[] hash = digest.digest();

			StringBuilder builder = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
				builder.append(HEX_CHARS[(b & 0xF0) >> 4]);
				builder.append(HEX_CHARS[b & 0x0F]);
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
