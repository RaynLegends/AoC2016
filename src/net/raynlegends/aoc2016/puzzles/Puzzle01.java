package net.raynlegends.aoc2016.puzzles;

import java.util.HashMap;
import java.util.Map.Entry;

public class Puzzle01 implements Puzzle {

	@Override
	public String calculatePart1(String input) {
		int distance = 0;
		
		int[] directionMovements = new int[4];
		int currentDirection = 0;
		
		String[] instructions = input.split(", ");
		for (String instruction : instructions) {
			char direction = instruction.charAt(0);
			
			if (direction == 'L') {
				currentDirection = (currentDirection + 3) % 4;
			} else if (direction == 'R') {
				currentDirection = (currentDirection + 1) % 4;
			}
			
			int movements = Integer.parseInt(instruction.substring(1));
			directionMovements[currentDirection] += movements;
		}
		
		int x = directionMovements[0] - directionMovements[2];
		int y = directionMovements[1] - directionMovements[3];
		distance = Math.abs(x) + Math.abs(y);
		
		return distance + "";
	}
	
	@Override
	public String calculatePart2(String input) {
		HashMap<String, Integer> visited = new HashMap<>();
		int[] directionMovements = new int[4];
		int currentDirection = 0;
		
		String[] instructions = input.split(", ");
		for (String instruction : instructions) {
			char direction = instruction.charAt(0);
			
			if (direction == 'L') {
				currentDirection = (currentDirection + 3) % 4;
			} else if (direction == 'R') {
				currentDirection = (currentDirection + 1) % 4;
			}
			
			int movements = Integer.parseInt(instruction.substring(1));
			
			for (int i = 1; i <= movements; i++) {
				directionMovements[currentDirection] += 1;
				
				int x = directionMovements[0] - directionMovements[2];
				int y = directionMovements[1] - directionMovements[3];
				String id = x + ";" + y;
				for (Entry<String, Integer> other : visited.entrySet()) {
					if (other.getKey().equals(id)) {
						return other.getValue() + "";
					}
				}
				int distance = Math.abs(x) + Math.abs(y);
				
				visited.put(id, distance);
			}
		}

		return -1 + "";
	}

}
