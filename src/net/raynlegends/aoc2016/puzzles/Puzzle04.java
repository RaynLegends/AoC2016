package net.raynlegends.aoc2016.puzzles;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Puzzle04 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		int total = 0;

		for (String line : input.split("\n")) {
			String[] parts = line.split("-");
			int last = parts.length - 1;

			String[] lastPart = parts[last].split("\\[");
			int id = Integer.parseInt(lastPart[0]);
			String checksum = lastPart[1].replace("]", "").trim();

			final HashMap<Character, Integer> characterCounts = new HashMap<>();
			for (int i = 0; i < last; i++) {
				for (char character : parts[i].toCharArray()) {
					Integer previousCount = characterCounts.get(character);

					if (previousCount == null) {
						previousCount = 0;
					}

					characterCounts.put(character, previousCount + 1);
				}
			}

			LinkedList<Character> correctChecksumList = new LinkedList<>();
			correctChecksumList.addAll(characterCounts.keySet());
			Collections.sort(correctChecksumList, new Comparator<Character>() {

				@Override
				public int compare(Character character1, Character character2) {
					int count1 = characterCounts.get(character1);
					int count2 = characterCounts.get(character2);

					if (count1 > count2) {
						return -1;
					} else if (count1 < count2) {
						return 1;
					}

					return character1.compareTo(character2);
				}

			});

			StringBuilder correctChecksum = new StringBuilder();
			for (Character character : correctChecksumList) {
				correctChecksum.append(character);

				if (correctChecksum.length() == 5) {
					break;
				}
			}

			if (correctChecksum.toString().equals(checksum)) {
				total += id;
			}

			System.out.println("-" + correctChecksum.toString() + "- -" + checksum + "-");
		}

		return total + "";
	}

	private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	@Override
	public String calculatePart2(String input) {
		for (String line : input.split("\n")) {
			String[] parts = line.split("-");
			int last = parts.length - 1;

			String[] lastPart = parts[last].split("\\[");
			int id = Integer.parseInt(lastPart[0]);

			StringBuilder name = new StringBuilder();
			for (int i = 0; i < last; i++) {
				for (char character : parts[i].toCharArray()) {
					char newCharacter = character;
					for (int j = 0; j < id; j++) {
						newCharacter = alphabet[(Arrays.binarySearch(alphabet, newCharacter) + 1) % alphabet.length];
					}
					name.append(newCharacter);
				}
				name.append(' ');
			}

			if (name.toString().trim().equals("northpole object storage")) {
				return id + "";
			}
		}

		return "Not found";
	}

}
