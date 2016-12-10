package net.raynlegends.aoc2016.puzzles;

public class Puzzle03 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		int validCount = 0;

		int[] sides = new int[3];
		lines:for (String line : input.split("\n")) {
			sides[0] = parse(line.substring(0, 5));
			sides[1] = parse(line.substring(5, 10));
			sides[2] = parse(line.substring(10, 15));

			if (!checkTriangle(sides)) {
				continue lines;
			}

			validCount++;
		}

		return validCount + "";
	}

	@Override
	public String calculatePart2(String input) {
		String[] lines = input.split("\n");

		int[][] map = new int[3][lines.length];
		int row = 0;
		for (String line : lines) {
			map[0][row] = parse(line.substring(0, 5));
			map[1][row] = parse(line.substring(5, 10));
			map[2][row] = parse(line.substring(10, 15));
			row++;
		}

		int validCount = 0;

		int side = 0;
		int[] sides = new int[3];
		for (int y = 0; y < 3; y++) {
			lines:for (int x = 0; x < lines.length; x++) {
				sides[side++] = map[y][x];

				if (side != 3) {
					continue;
				}

				side = 0;

				if (!checkTriangle(sides)) {
					continue lines;
				}

				validCount++;
			}
		}

		return validCount + "";
	}
	
	private int parse(String line) {
		return Integer.parseInt(line.replace(" ", ""));
	}
	
	private boolean checkTriangle(int[] sides) {
		for (int i = 0; i < sides.length; i++) {
			if (sides[i % sides.length] + sides[(i + 1) % sides.length] <= sides[(i + 2) % sides.length]) {
				return false;
			}
		}
		
		return true;
	}

}
