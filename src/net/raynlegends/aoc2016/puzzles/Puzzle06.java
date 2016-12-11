package net.raynlegends.aoc2016.puzzles;

import java.util.HashMap;
import java.util.Map.Entry;

public class Puzzle06 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		String[] lines = input.split("\n");

		int rows = lines.length;
		int columns = lines[0].trim().length();

		HashMap<Integer, HashMap<Character, Integer>> totalCharacterCounts = new HashMap<>();
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				HashMap<Character, Integer> characterCounts = totalCharacterCounts.get(x);
				if (characterCounts == null) {
					characterCounts = new HashMap<>();
					totalCharacterCounts.put(x, characterCounts);
				}
				
				char character = lines[y].charAt(x);

				Integer count = characterCounts.get(character);

				if (count == null) {
					count = 0;
				}

				characterCounts.put(character, count + 1);
			}
		}

		StringBuilder message = new StringBuilder();
		for (int x = 0; x < columns; x++) {
			HashMap<Character, Integer> characterCounts = totalCharacterCounts.get(x);
			
			Character best = null;
			int bestCount = -1;
			for (Entry<Character, Integer> entry : characterCounts.entrySet()) {
				Character character = entry.getKey();
				int count = entry.getValue();
				
				if (count > bestCount) {
					best = character;
					bestCount = count;
				}
			}
			
			message.append(best);
		}

		return message.toString();
	}

	@Override
	public String calculatePart2(String input) {
		String[] lines = input.split("\n");

		int rows = lines.length;
		int columns = lines[0].trim().length();

		HashMap<Integer, HashMap<Character, Integer>> totalCharacterCounts = new HashMap<>();
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				HashMap<Character, Integer> characterCounts = totalCharacterCounts.get(x);
				if (characterCounts == null) {
					characterCounts = new HashMap<>();
					totalCharacterCounts.put(x, characterCounts);
				}
				
				char character = lines[y].charAt(x);

				Integer count = characterCounts.get(character);

				if (count == null) {
					count = 0;
				}

				characterCounts.put(character, count + 1);
			}
		}

		StringBuilder message = new StringBuilder();
		for (int x = 0; x < columns; x++) {
			HashMap<Character, Integer> characterCounts = totalCharacterCounts.get(x);
			
			Character best = null;
			int bestCount = Integer.MAX_VALUE;
			for (Entry<Character, Integer> entry : characterCounts.entrySet()) {
				Character character = entry.getKey();
				int count = entry.getValue();
				
				if (count < bestCount) {
					best = character;
					bestCount = count;
				}
			}
			
			message.append(best);
		}

		return message.toString();
	}

}
