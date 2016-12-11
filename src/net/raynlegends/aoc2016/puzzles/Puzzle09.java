package net.raynlegends.aoc2016.puzzles;

public class Puzzle09 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		input = input.trim();
		int length = input.length();
		
		boolean marker1part = false;
		boolean marker2part = false;
		int markerStart = 0;
		StringBuilder marker1 = null, marker2 = null;
		int marker1value = 0, marker2value = 0;
		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);
			
			if (character == ' ') {
				length--;
			} else if (character == '(') {
				marker1part = true;
				marker1 = new StringBuilder();
				marker2 = new StringBuilder();
				markerStart = i;
			} else if (character == 'x') {
				if (!marker1part) {
					continue;
				}
				
				marker1part = false;
				marker2part = true;
				marker1value = Integer.parseInt(marker1.toString());
			} else if (character == ')') {
				if (!marker2part) {
					continue;
				}
				
				marker2part = false;
				marker2value = Integer.parseInt(marker2.toString());
				
				length -= (i - markerStart) + 1;
				length += marker1value * (marker2value - 1);
				i += marker1value;
			} else {
				if (marker1part) {
					marker1.append(character);
				} else if (marker2part) {
					marker2.append(character);
				}
			}
		}
		
		return length + "";
	}

	@Override
	public String calculatePart2(String input) {
		input = input.trim();
		long length = input.length();
		
		length = check(input, length);
		return length + "";
	}

	private long check(String input, long length) {
		boolean marker1part = false;
		boolean marker2part = false;
		int markerStart = 0;
		StringBuilder marker1 = null, marker2 = null;
		int marker1value = 0, marker2value = 0;
		for (int i = 0; i < input.length(); i++) {
			char character = input.charAt(i);
			
			if (character == ' ') {
				length--;
			} else if (character == '(') {
				marker1part = true;
				marker1 = new StringBuilder();
				marker2 = new StringBuilder();
				markerStart = i;
			} else if (character == 'x') {
				if (!marker1part) {
					continue;
				}
				
				marker1part = false;
				marker2part = true;
				marker1value = Integer.parseInt(marker1.toString());
			} else if (character == ')') {
				if (!marker2part) {
					continue;
				}
				
				marker2part = false;
				marker2value = Integer.parseInt(marker2.toString());
				
				length -= (i - markerStart) + 1;
				length += marker1value * (marker2value - 1);
				
				int max = i + marker1value + 1;
				if (max > input.length()) {
					max = input.length();
				}
				
				for (int j = 0; j < marker2value; j++) {
					length = check(input.substring(i, max), length);
				}
				i += marker1value;
			} else {
				if (marker1part) {
					marker1.append(character);
				} else if (marker2part) {
					marker2.append(character);
				}
			}
		}
		
		return length;
	}

}
