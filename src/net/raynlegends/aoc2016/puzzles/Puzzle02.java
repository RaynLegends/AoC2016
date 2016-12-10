package net.raynlegends.aoc2016.puzzles;

public class Puzzle02 implements Puzzle {

	private short[][] keypad1 = new short[][] { 
		{1, 2, 3},
		{4, 5, 6},
		{7, 8, 9}
	};

	@Override
	public String calculatePart1(String input) {
		int y = 1;
		int x = 1;

		StringBuilder sequence = new StringBuilder();
		for (String line : input.split("\n")) {
			for (char character : line.toCharArray()) {
				int offsetY = 0;
				int offsetX = 0;

				if (character == 'U') {
					offsetY = -1;
				} else if (character == 'R') {
					offsetX = 1;
				} else if (character == 'D') {
					offsetY = 1;
				} else if (character == 'L') {
					offsetX = -1;
				}

				if (y + offsetY >= 0 && y + offsetY < 3) {
					y += offsetY;
				}
				if (x + offsetX >= 0 && x + offsetX < 3) {
					x += offsetX;
				}
			}

			sequence.append(keypad1[y][x]);
		}

		return sequence.toString();
	}
	
	private char[][] keypad2 = new char[][] { 
		{0x0, 0x0, '1', 0x0, 0x0},
		{0x0, '2', '3', '4', 0x0},
		{'5', '6', '7', '8', '9'},
		{0x0, 'A', 'B', 'C', 0x0},
		{0x0, 0x0, 'D', 0x0, 0x0},
	};

	@Override
	public String calculatePart2(String input) {
		int y = 2;
		int x = 0;

		StringBuilder sequence = new StringBuilder();
		for (String line : input.split("\n")) {
			for (char character : line.toCharArray()) {
				int offsetY = 0;
				int offsetX = 0;

				if (character == 'U') {
					offsetY = -1;
				} else if (character == 'R') {
					offsetX = 1;
				} else if (character == 'D') {
					offsetY = 1;
				} else if (character == 'L') {
					offsetX = -1;
				}

				if (y + offsetY >= 0 && y + offsetY < 5 && keypad2[x][y + offsetY] != 0) {
					y += offsetY;
				}
				if (x + offsetX >= 0 && x + offsetX < 5 && keypad2[x + offsetX][y] != 0) {
					x += offsetX;
				}
			}

			sequence.append(keypad2[y][x]);
		}

		return sequence.toString();
	}

}
