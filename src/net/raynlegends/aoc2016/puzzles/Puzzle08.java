package net.raynlegends.aoc2016.puzzles;

public class Puzzle08 implements Puzzle {

	private int columns = 50;
	private int rows = 6;
	private boolean[][] screen;

	@Override
	public String calculatePart1(String input) {
		screen = new boolean[rows][columns];
		
		for (String line : input.split("\n")) {
			String[] command = line.trim().split(" ");

			if (command[0].equals("rect")) {
				String[] rect = command[1].split("x");
				int rectX = Integer.parseInt(rect[0]);
				int rectY = Integer.parseInt(rect[1]);

				for (int y = 0; y < rectY; y++) {
					for (int x = 0; x < rectX; x++) {
						screen[y][x] = true;
					}
				}
			} else if (command[0].startsWith("rotate")) {
				if (command[1].equals("column")) {
					int column = Integer.parseInt(command[2].substring(2));
					int offset = rows - Integer.parseInt(command[4]);
					
					boolean[] copy = new boolean[rows];
					for (int y = 0; y < rows; y++) {
						copy[y] = screen[y][column];
					}
					
					for (int y = 0; y < rows; y++) {
						screen[y][column] = copy[(y + offset) % rows];
					}
				} else if (command[1].equals("row")) {
					int row = Integer.parseInt(command[2].substring(2));
					int offset = columns - Integer.parseInt(command[4]);
					
					boolean[] copy = new boolean[columns];
					for (int x = 0; x < columns; x++) {
						copy[x] = screen[row][x];
					}
					
					for (int x = 0; x < columns; x++) {
						screen[row][x] = copy[(x + offset) % columns];
					}
				}
			}
		}

		int count = 0;
		
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				if (screen[y][x]) {
					count++;
				}
			}
		}
		
		return count + "";
	}
	
	@Override
	public String calculatePart2(String input) {
		calculatePart1(input);
		
		StringBuilder result = new StringBuilder();
		result.append("\n");
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				result.append(screen[y][x] ? "#" : " ");
			}
			result.append("\n");
		}
		result.append("\n");
		
		return result.toString();
	}

}
