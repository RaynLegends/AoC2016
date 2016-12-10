package net.raynlegends.aoc2016.managers;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.raynlegends.aoc2016.puzzles.Puzzle;

public class PuzzleManager {

	private static final int implemented = 3;
	private Map<String, Puzzle> puzzles = new LinkedHashMap<>();

	public PuzzleManager() {
		for (int i = 0; i < implemented; i++) {
			String id = String.format("%02d", i + 1);

			try {
				Class<?> puzzleClass = Class.forName("net.raynlegends.aoc2016.puzzles.Puzzle" + id);
				Puzzle puzzle = (Puzzle) puzzleClass.newInstance();
				puzzles.put("Puzzle " + id, puzzle);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public Collection<String> getPuzzles() {
		return puzzles.keySet();
	}
	
	public Puzzle getPuzzle(String key) {
		return puzzles.get(key);
	}
	
}
