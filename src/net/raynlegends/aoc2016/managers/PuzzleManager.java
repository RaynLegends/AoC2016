package net.raynlegends.aoc2016.managers;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.raynlegends.aoc2016.puzzles.Puzzle;

public class PuzzleManager {

	private Map<String, Puzzle> puzzles = new LinkedHashMap<>();

	public PuzzleManager() {
		int i = 1;
		while (true) {
			String id = String.format("%02d", i++);

			try {
				Class<?> puzzleClass = Class.forName("net.raynlegends.aoc2016.puzzles.Puzzle" + id);
				Puzzle puzzle = (Puzzle) puzzleClass.newInstance();
				puzzles.put("Puzzle " + id, puzzle);
			} catch (ClassNotFoundException e) {
				break;
			} catch (InstantiationException | IllegalAccessException e) {
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
