package net.raynlegends.aoc2016.puzzles;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

public class Puzzle07 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		int tls = 0;

		lines:for (String line : input.split("\n")) {
			String[] parts = line.split("\\[|]");

			boolean valid = false;
			for (int i = 0; i < parts.length; i++) {
				String part = parts[i].trim();

				if (checkAbba(part)) {
					if (i % 2 == 1) {
						continue lines;
					}
					
					valid = true;
				}
			}
			
			if (valid) {
				tls++;
			}
		}

		return tls + "";
	}

	private boolean checkAbba(String string) {
		return string.matches(".*([a-z])(?!\\1)([a-z])\\2\\1.*");
	}

	public String calculatePart2z(String input) {
		int ssl = 0;
		
		for (String line : input.split("\n")) {
			if (line.trim().matches(".*(([a-z])(?!\\2)([a-z])\\2)(?=.*(\\3\\2\\3).*).*")) {
				ssl++;
			}
		}
		
		return ssl + "";
	}
	
	public String calculatePart2(String input) {
		int ssl = 0;

		lines:for (String line : input.split("\n")) {
			String[] parts = line.trim().split("\\[|]");

			List<Sequence> abaSequences = new ArrayList<>();
			for (int i = 0; i < parts.length; i = i + 2) {
				String part = parts[i].trim();
				searchAbas(part, abaSequences);
			}
			
			if (abaSequences.size() == 0) {
				continue;
			}
			
			List<Sequence> babSequences = new ArrayList<>();
			for (int i = 1; i < parts.length; i = i + 2) {
				String part = parts[i].trim();
				searchAbas(part, babSequences);
			}
			
			for (Sequence abaSequence : abaSequences) {
				for (Sequence babSequence : babSequences) {
					if (abaSequence.a == babSequence.b && abaSequence.b == babSequence.a) {
						ssl++;
						continue lines;
					}
				}
			}
		}
		
		return ssl + "";
	}
	
	@AllArgsConstructor
	private class Sequence {
		
		private char a;
		private char b;
		
	}
	
	private void searchAbas(String string, List<Sequence> sequences) {
		for (int i = 0; i < string.length() - 2; i++) {
			char char1 = string.charAt(i);
			char char2 = string.charAt(i + 1);
			char char3 = string.charAt(i + 2);
			
			if (char1 == char3 && char1 != char2) {
				sequences.add(new Sequence(char1, char2));
			}
		}
	}

}
